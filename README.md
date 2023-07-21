# Auction System

Welcome to the Auction System, a powerful web application designed to create an immersive and seamless auction experience. Our platform offers a feature-rich environment where users can participate in auctions.

## Introduction
In today's fast-paced world, online auctions have become a popular and efficient way to conduct sales, connect buyers with unique electronic items, and provide sellers with a vast marketplace. The Auction System aims to streamline this process, providing a secure and user-friendly platform for users of all backgrounds to engage in auctions for electronics with confidence.

## Key Features
1. Accounts and Authentication
Our system offers a robust account creation and authentication process, ensuring that users' personal information remains secure. Upon registration, users are guided to their respective portals based on their roles, whether they are end-users, customer representatives, or administrators.

2. Auctions
Sellers can effortlessly create auctions, offering their items for sale to interested bidders. With the flexibility to set closing dates, hidden minimum prices, a minimum increment between bids, and other item characteristics, sellers can tailor their auctions to achieve optimal results. Buyers, on the other hand, can place bids on items they desire, and automatic bidding further enhances the experience by allowing them to set secret upper limits and bid range.

3. Browsing and Advanced Search Functionality
Our platform empowers users to explore a diverse range of items and stay informed about the current bidding status. With advanced search options, users can easily find items based on specific criteria, making it convenient to locate their desired items. Additionally, users can access bid histories, view participation records, and set alerts for items of interest.

4. Customer Representative Functions
Dedicated customer representatives play a pivotal role in ensuring a smooth auction experience. They have the authority to address user queries promptly, edit account information if needed, and maintain the integrity of the auction environment by removing bids and auctions when necessary.

5. Admin Functions
Administrators wield administrative privileges to oversee the platform effectively. They can create accounts for customer representatives, access sales reports, including total earnings, and identify best-selling items, contributing to the platform's continuous improvement.

6. Additional Features
To enhance the user experience and system performance, we've implemented several additional features:

Auto Bidding: Our Auto Bidding mechanism, powered by PL/SQL, ensures quicker execution and helps users secure their desired items effortlessly.
Encryption: Encryption using AES safeguards users' Personally Identifiable Information (PII), instilling confidence in the platform's security.
SQL Triggers: SQL Triggers have been deployed to check bid status and determine auction winners accurately.
Email Alerts: Users will receive timely alerts via email to stay informed about their auction, if they someone outbidded them, auction/bid results, and if their listed favorite item becomes available.

## Getting Started

To experience the exciting world of auctions and join our dynamic community, follow the installation instructions provided below. Discover unique items, place bids, and unleash the thrill of competitive auctions with the Auction System!

Feel free to explore our [Presentation]<https://drive.google.com/file/d/1BxnZgO_Baovy-Le6Kmixl1WGmrmiGGeJ/view?usp=share_link> to learn more about the platform's capabilities.

We hope you enjoy the Auction System and find it to be a valuable addition to your online auction journey! Happy bidding!

Steps to run the AuctionSystem:

1. Clone the repository
2. Install the dependencies: Java and Tomcat server
3. Include the below listed JAR files.
3. Set up the database (SQL dump present in the /Db_Dump/db_dump.sql file), and configure the connection.
4. Set max_sp_recursion_depth to more than 0. Required for auto-bid functionality to work. Syntax: 'SET max_sp_recursion_depth = 200;'. For reference, [read this article]<https://dev.mysql.com/doc/refman/5.7/en/stored-routines-syntax.html>.
5. Start the application by hosting the AuctionSystem. Home Page `landingpage.jsp` will be loaded.

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

# Demo Users:
- End User role: emp1@auction.com/cust1@123, emp1@gmail.com/cust2@123
- Customer Representative role: emp1@auction.com/emp1@123, emp2@auction.com/emp2@123
- Admin role: admin@auction.com/admin@123

# Functionality with Screenshots:
### Home page for the application:
![Alt text](/img/HomePage.png?raw=true "Home Page")

### Listing an Item for Auction:
End users can list an item for Auction as follows. End user can also add an item in case if the item is not available in the inventory.
[Note: Customer Representatives will be monitoring the system for any illegal/duplicate items.]
![Alt text](/img/ToPlaceAuction.png?raw=true "Listing an Item for Auction")

### Placing Bids on Items
End users can then place bids on the items available for Auction. They have an option to either choose for a static bid, or enabling an auto-bid option (that is, bidding in a range of price). The least possible bid amount will be selected based on current bids, and the user's bid will be adjusted accordingly as other bidder's bids on the same product.
![Alt text](/img/UserPlacesBidAutoIncrement.png?raw=true "Placing Bids on the Items")

### Auto-Bidding Example
For example, check the bids by the user Kunjan and Cust One (shown in their respective dashboards), both of whom bid on same smartphone. Here, Cust One outbid Kunjan's bid and is winning by a margin of 5 USD.
![Alt text](/img/UserAutoBidExampleLoser.png?raw=true "Kunjan's Dashboard (Loser)")
![Alt text](/img/UserAutoBidExampleWinner.png?raw=true "Cust One's Dashboard (Winner)")

[Note: Sellers have an option of providing a minimum increment in between each bid, which is set to 5 USD for this auction.]

#### Adding Items to WishList

End user can add an item to WishList. Once an item is added, the system will send an email to the user's registered email if there are any auction listings available for the item:
![Alt text](/img/WishList.png?raw=true "Adding Items to the WishList")


#### End User Dashboard:
As part of the dashboard available for end users, they can:

- Check the summary of all the items they have put on Auction:
![Alt text](/img/EndUserDashboardAuctionListing.png?raw=true "EndUserDashboardAuctionListing")

- Check the summary of all the bids they have placed on available Auctions:
![Alt text](/img/UserDashBoardPlacedBids.png?raw=true "UserDashBoardPlacedBids")


### Customer Representative Functions
Customer Representatives can approve/deny multiple end user requests simultaneously, and the system will perform the necessary upgrades accordingly. Customer Representatives can also answer user queries as shown below.
![Alt text](/img/CustomerRepresenativeActions.png?raw=true "Customer Represenative Actions")


### Admin Dashboard
Dashboard available for the Admin user to check the reports of the entire system:
![Alt text](/img/AdminSalesDashboard1.png?raw=true "Admin Sales Dashboard1")

![Alt text](/img/AdminSalesDashboard2.png?raw=true "Admin Sales Dashboard2")

![Alt text](/img/AdminSalesDashboard3.png?raw=true "Admin Sales Dashboard3")

### Managing Customer Representatives
Admin is the only one who can create/modify/delete a Customer Representative account for security purposes.
![Alt text](/img/CustomerRepresentativeRegistraionForm.png?raw=true "Customer Representative Registraion Form")



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
