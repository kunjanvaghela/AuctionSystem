# AuctionSystem

Presentation Link: https://drive.google.com/file/d/1BxnZgO_Baovy-Le6Kmixl1WGmrmiGGeJ/view?usp=share_link

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


# Auction System

This is a web application for an auction platform that allows users to create accounts, log in and out, and participate in auctions. It provides features such as auction creation, bidding, browsing, advanced search, and administrative functions.

## Project Milestones

### I. Accounts and Authentication

- [ ] Users can create accounts and login/logout.
- [ ] Users will be guided towards either the End User portal, or Customer Representative portal, or Admin portal based on their role.

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

## Getting Started

To run this project locally, follow these steps:

1. Clone the repository
2. Install the dependencies: Java and Tomcat server
3. Set up the database and configure the connection.
4. Start the application from `landingpage.jsp`

## Technologies Used

- Java
- MySQL
- HTML/CSS
- JavaScript
- Bootstrap
