# Auction System

This is a web application for an auction platform that allows users to create accounts, log in and out, and participate in
auctions. It provides features such as auction creation, bidding, browsing, advanced search, and administrative functions.

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

## Getting Started

To run this project locally, follow these steps:

1. Clone the repository
2. Install the dependencies: Java and Tomcat server
3. Set up the database and configure the connection.
4. Start the application from `landingpage.jsp`

## Technologies Used

- Java
- MySQL
- PL/SQL
- HTML/CSS
- JavaScript
- Bootstrap

# Presentation Link:
https://drive.google.com/file/d/1BxnZgO_Baovy-Le6Kmixl1WGmrmiGGeJ/view?usp=share_link


# Screenshots:
![Alt text](relative%20/main/webapp/sony1.png?raw=true "Title")
![Alt text](relative%20/img/IMG_3491.JPG?raw=true "Title")
