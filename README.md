# Auction System

This is a web application for an auction platform that allows users to create accounts, log in and out, and participate in
auctions. It provides features such as auction creation, bidding, browsing, advanced search, and administrative functions.


## Getting Started

To run this project locally, follow these steps:

1. Clone the repository
2. Install the dependencies: Java and Tomcat server
3. Set up the database and configure the connection.
4. Start the application from `landingpage.jsp`

## Tech Stack

- Java
- MySQL
- PL/SQL
- HTML/CSS
- JavaScript
- Bootstrap

## JAR needed/Libraries used
- javax.mail
- mysql-connector-java-5.1.49
- taglibs-standard-impl-1.2.5
- taglibs-standard-spec-1.2.5

# Presentation Link:
https://drive.google.com/file/d/1BxnZgO_Baovy-Le6Kmixl1WGmrmiGGeJ/view?usp=share_link

# Demo Users:
- End User role: emp1@auction.com/cust1@123, emp1@gmail.com/cust2@123
- Customer Representative role: emp1@auction.com/emp1@123, emp2@auction.com/emp2@123
- Admin role: admin@auction.com/admin@123

# Functionality with Screenshots:
Home page for the application:
![Alt text](/img/HomePage.png?raw=true "HomePage")

End users can list an item for Auction as follows. And End user can also add an item in case if the item is not available in the inventory.
[Note: Customer Representatives will be monitoring the system for any illegal/duplicate items.]
![Alt text](/img/ToPlaceAuction.png?raw=true "ToPlaceAuction")

End users can then place bids on the items available for Auction. End users have an option to either choose for a static bid, or can enable an auto-bid option (that is, bidding in a range of price). The least possible bid amount will be selected based on current bids. Current bid of the user will be moved up or down based on bid amount changes by other bidders on the same product.
![Alt text](/img/UserPlacesBidAutoIncrement.png?raw=true "UserPlacesBidAutoIncrement")

For example, check the bids by the user Kunjan and Cust One (shown respective dashboards of the users), both of them bid on same smartphone. Here, Cust One outbid Kunjan's bid, and is winning by a margin of 5 USD.
![Alt text](/img/UserAutoBidExampleLoser.png?raw=true "UserAutoBidExampleLoser")
![Alt text](/img/UserAutoBidExampleWinner.png?raw=true "UserAutoBidExampleWinner")

[Note: Sellers have an option of providing a minimum increment in between each bid. Since the seller of the listed auction set this limit as 5, there is a difference of at least 5 USD between the two given bids]

End user can add an item to WishList. Once an item is added, system will send email to the user's registered email if there are any auction listing available on the item:
![Alt text](/img/WishList.png?raw=true "WishList")


### As part of Dashboard available for the end users, they can:
- Check the summary of all the items put on Auction by the user:
![Alt text](/img/EndUserDashboardAuctionListing.png?raw=true "EndUserDashboardAuctionListing")

- Check the summary of all the bids on the available Auctions:
![Alt text](/img/UserDashBoardPlacedBids.png?raw=true "UserDashBoardPlacedBids")


Customer Representatives can approve/deny multiple end user requests simultaneously (system will do the necessary upgrades as per the action selected). Customer Representative can also answer user queries as shown below.
![Alt text](/img/CustomerRepresenativeActions.png?raw=true "CustomerRepresenativeActions")

Dashboard available for the Admin user to check the reports of the entire system:
![Alt text](/img/AdminSalesDashboard1.png?raw=true "AdminSalesDashboard1")

![Alt text](/img/AdminSalesDashboard2.png?raw=true "AdminSalesDashboard2")

![Alt text](/img/AdminSalesDashboard3.png?raw=true "AdminSalesDashboard3")

Admin is also the only one who can create/modify/delete a Customer Representative account for security purpose:
![Alt text](/img/CustomerRepresentativeRegistraionForm.png?raw=true "CustomerRepresentativeRegistraionForm")



## Project Milestones

### I. Accounts and Authentication

- [ ] Users can create accounts and login/logout.
- [ ] Users will be guided towards either the End User portal, or Customer Representative portal, or Admin portal based on
their role.

### II. Auctions

- [ ] Sellers can create auctions and post items for sale, with a closing date time and optionally a hidden minimum price.
- [ ] Sellers can set all the characteristics of the item.
- [ ] Buyers can place bids on the item.
- [ ] Automatic bidding: Buyers can set a secret upper limit and bid increment.
- [ ] Alert buyers if someone bids more than their upper limit (automatic).
- [ ] Define the winner of the auction.
- [ ] Check if the seller has set a reserve when the closing time has come.
- [ ] If a reserve is set and higher than the last bid, no one is the winner.
- [ ] If no reserve is set, the highest bidder wins the auction.
- [ ] Alert the winner that they have won the auction.

### III. Browsing and Advanced Search Functionality

- [ ] Users can browse items and see the current bidding status.
- [ ] Users can sort items by different criteria (type, bidding price, etc.).
- [ ] Users can search for items using various criteria.
- [ ] Users can view the bid history for a specific auction.
- [ ] Users can view the list of auctions a specific buyer or seller has participated in.
- [ ] Users can set alerts for specific items they are interested in.
- [ ] Users receive alerts when the item becomes available.
- [ ] Users can browse and questions and answers.

### IV. Customer Representative Functions

- [ ] Customer representatives can respond to user questions.
- [ ] Customer representatives can edit and delete account information.
- [ ] Customer representatives can remove bids and auctions.

### V. Admin Functions

- [ ] Admin can create accounts for customer representatives.
- [ ] Admin can generate sales reports, including total earnings.
- [ ] Admin can view best-selling items.

### VI. Additional Features
- [ ] Auto Bidding implemented using PL/SQL for quicker execution
- [ ] Encrypted PII data using AES Encryption
- [ ] SQL Triggers implemented for checking bid status and wins.
- [ ] Alerts sent via Email.
