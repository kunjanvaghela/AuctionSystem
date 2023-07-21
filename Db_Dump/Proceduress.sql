-- Event Triggers
use auctionsystem;
SHOW Processlist;
SHOW events;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_bids`(IN checkListingID INT)
BEGIN
	-- DECLARE current_bid FLOAT;
    DECLARE initial_pricee FLOAT;
	DECLARE curr_bidId INT;
    DECLARE curr_lowest_bid FLOAT;
	DECLARE curr_highest_bid FLOAT;
	DECLARE bid_increment1 FLOAT;
	DECLARE curr_best_bid_amount FLOAT;
	DECLARE best_bidId1 INT;
    DECLARE flag INT;
    DECLARE no_of_bids INT;
    DECLARE next_bid_amount FLOAT;
    DECLARE done INT DEFAULT FALSE;
    DECLARE cur1 CURSOR FOR SELECT bidId from BIDS where listingId = checkListingID;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    lebel1: BEGIN
		SET flag = 1;

		SELECT count(*) INTO no_of_bids from BIDS where listingId = checkListingID;

		-- WHILE flag=1 DO
		-- Get the current best bid details
		SELECT current_best_bid_amount, best_bidId, initial_price, bid_increment
		INTO curr_best_bid_amount, best_bidId1, initial_pricee, bid_increment1
		FROM item_listing
		WHERE listingId = checkListingID;

        -- Get the next bid amount that other bidders must have
		IF curr_best_bid_amount IS NULL THEN
			SET next_bid_amount = initial_pricee;
		ELSE
			SET next_bid_amount = curr_best_bid_amount + bid_increment1;
		END IF;

        -- Check if there is at least 1 Bid for the given listing
		IF no_of_bids > 0 THEN

			-- If yes, then check each bidId for proper conditions
			OPEN cur1;
			read_loop: LOOP
				FETCH cur1 INTO curr_bidId;
				IF done THEN
					LEAVE read_loop;
				END IF;
				-- select curr_bidId, next_bid_amount, best_bidId1;

                IF best_bidId1 = curr_bidId THEN
					select "Do nothing as best_bidId=curr_bidId";
                ELSE
					-- Get lowest_bid and highest_bid amounts of the current bidder.
					SELECT lowest_bid, highest_bid INTO curr_lowest_bid, curr_highest_bid FROM BIDS where bidId = curr_bidId;
					-- select curr_lowest_bid, curr_highest_bid;
					IF next_bid_amount <= curr_highest_bid THEN
						SET flag = 0;
						IF next_bid_amount < curr_lowest_bid THEN
							-- Updating the item_listing with current best bids
							UPDATE item_listing SET
								current_best_bid_amount = curr_lowest_bid,
								best_bidId = curr_bidId
							WHERE listingId = checkListingID;
							-- Updating the bid table with best bid details
							UPDATE Bids SET
								current_bid = curr_lowest_bid
							WHERE bidId = curr_bidId;
						ELSE
							-- Updating the item_listing with current best bids
							UPDATE item_listing SET
								current_best_bid_amount = next_bid_amount,
								best_bidId = curr_bidId
							WHERE listingId = checkListingID;
							-- Updating the bid table with best bid details
							UPDATE Bids SET
								current_bid = next_bid_amount
							WHERE bidId = curr_bidId;
						END IF;
                        -- select curr_bidId, next_bid_amount, best_bidId1, flag;
						LEAVE read_loop;
					ELSE
						-- Updating the bid table with current bid Id's highest bid as he can't win the bid with current bid
						UPDATE Bids SET
							current_bid = highest_bid
						WHERE bidId = curr_bidId;
					END IF;
				End IF;
			END LOOP;
			CLOSE cur1;
		END IF;

		IF flag = 0 THEN
 			CALL check_bids(checkListingID);
            -- select "Called for Flag 0";
 		END IF;
	-- END WHILE;
    END lebel1;
END ;;
DELIMITER ;




-- Item_Listing and Bids closing event:
-- Procedure-
DELIMITER $$
DROP PROCEDURE close_ItemListing_Bids $$
CREATE PROCEDURE close_ItemListing_Bids()
BEGIN
    UPDATE item_listing SET current_status='I' where current_status IS null;
    UPDATE item_listing SET current_status='A' where closing_timestamp > current_timestamp() and current_status IN ('I');
    UPDATE item_listing SET current_status='X' where closing_timestamp <= current_timestamp() and current_status IN ('I','A');

    UPDATE bids SET current_status='I' where current_status IS null;
    UPDATE bids set current_status='A' where current_status not in ('X','W') and listingid in (select listingid from item_listing where current_status='A');
    UPDATE bids set current_status='W' where current_status IN ('A','I') and bidId in (select best_bidId from item_listing where current_status='X' and current_best_bid_amount > min_bid_price);
	UPDATE bids set current_status='X' where current_status not in ('X','W') and listingid in (select listingid from item_listing where current_status='X');
END$$
DELIMITER ;


-- Event-
CREATE EVENT event_update_on_closing_timeestamp
	ON SCHEDULE EVERY 5 MINUTE
    STARTS CURRENT_TIMESTAMP
    ENDS CURRENT_TIMESTAMP + INTERVAL 1 YEAR
    DO
		CALL close_ItemListing_Bids();




-- Previous
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `close_ItemListing_Bids`()
BEGIN
	UPDATE item_listing SET current_status='X' where closing_timestamp <= current_timestamp() and current_status<>'X';
	UPDATE bids set current_status='X' where current_status<>'X' and listingid in (select listingid from item_listing where current_status='X');
    UPDATE item_listing SET current_status='A' where closing_timestamp > current_timestamp() and current_status='I';
END$$
DELIMITER ;
--
