# AuctionSystem

Presentation Link: https://drive.google.com/drive/u/1/folders/13Yq_Dmafw5hTANlrBZ40pwmmI4jXr0wz

# Implemented Features:
Create accounts of users; login, logout.
I. Auctions
a. seller creates auctions and posts items for sale
  1) set all the characteristics of the item
  2) set closing date and time
  3) set a hidden minimum price (reserve)
b. a buyer should be able to bid
  1) let the buyer set a new bid
  2) in case of automatic bidding set secret upper limit and bid increment
  3) alert other buyers of the item that a higher bid has been placed (manual)
  4) alert buyers in case someone bids more than their upper limit (automatic)
c. define the winner of the auction
  1) when the closing time has come, check if the seller has set a reserve
    a) if yes: if the reserve is higher than the last bid none is the winner.
    b) if no: whoever has the higher bid is the winner
      - alert the winner that they won the auction
II. Browsing and advanced search functionality
  a. let people browse on the items and see the status of the current bidding
  b. sort by different criteria (by type, bidding price etc.)
  c. search the list of items by various criteria.
  d. a user should be able to:
    1) view all the history of bids for any specific auction
    2) view the list of all auctions a specific buyer or seller has participated in
    3) view the list of "similar" items on auctions in the preceding month (and auction information about them)
  e. let user set an alert for specific items s/he is interested
    1) get an alert when the item becomes available
  f. users browse questions and answers
  g. users search questions by keywords
III. Admin and customer rep functions
  a. Admin (create an admin account ahead of time)
    1) creates accounts for customer representatives
    2) generates sales reports for:
      - total earnings
      - earnings per:
        - item
        - item type
        - end-user
      - best-selling items
      - best buyers
  b. Customer representative functions:
    1) users post questions to the customer representatives (i.e. customer service) [] reps reply to user questions
    2) edits and deletes account information
    3) removes bids
    4) removes auctions
