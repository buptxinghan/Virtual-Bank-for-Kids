# Virtual Bank Application for Kids

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Configuration](#configuration)
6. [Development Process](#development-process)
7. [API Documentation](#api-documentation)
8. [Contributing](#contributing)
9. [License](#license)
10. [Acknowledgments](#acknowledgments)

## Overview

The Virtual Bank Application for Kids is a Java-based standalone application designed to educate children about the value of money and the concept of banking. It encourages them to earn pocket money by completing tasks, save for goals, and spend responsibly. This project employs Agile methods for development, aiming to deliver a simple first release that can be extended in future iterations.

## Features

- **Account Creation, Deletion, Freezing, and Unfreezing**: Controlled through parental locks to ensure only parents can perform these actions.
- **Account Management**: Manage savings and current accounts, perform deposits, withdrawals, transfers, view transaction history, freeze and unfreeze accounts.
- **Balance Tracking**: Display the current balance.
- **Deposits and Withdrawals**: Allow children to deposit virtual money earned from tasks and make withdrawals.
- **Task Setting**: Parents can set tasks or activities (e.g., chores, exercises) through parental locks to provide earning opportunities for children.
- **Transaction History**: View transaction history.
- **Savings Goals**: Allow children to set savings goals and track progress through a pie chart. New goals can only be set once current goals are completed.
- **Interest**: Accounts accrue interest based on a set rate.
- **Instruction Manual**: Detailed usage guide, contact information for the development team, and answers to common user questions.
- **Parental Lock**: Differentiates actions performed by parents and children for account management.

## Installation

### Prerequisites

- **Java Development Kit (JDK) 22 or higher**
- **Apache Maven**

### Steps

#### Method 1: Download the project and run VirtualBankApplication.java

1. Clone the repository:
    ```bash
    git clone https://github.com/buptxinghan/Virtual-Bank-for-Kids.git
    cd VirtualBankV2
    ```

2. Build the project:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn exec:java -Dexec.mainClass="com.virtualbankv2.control.VirtualBankApplication"
    ```

#### Method 2: Run the application using the packaged jar file

1. Ensure you have the jar file (e.g., `virtual-bank-kids.jar`).

2. Run the application:

   ```bash
   java -jar VirtualBankV2.jar
   ```

## Usage

After running the application, users can interact through a graphical user interface (GUI). Upon logging in, the interface is divided into four sections: My Account, My Tasks, My Goals, and Instruction Manual.

### Parental Lock

**The password for the child lock is the root user's password 111.**

### Graphical User Interface

1. **My Account**
    - View current user's savings and current account information.
    - Perform deposit, withdrawal, and transfer operations.
    - View transaction history.
    - Freeze and unfreeze accounts.
    - Create, delete, freeze, and unfreeze accounts through parental locks to ensure only parents can perform these actions.

2. **My Tasks**
    - Parents can set tasks or activities (e.g., chores, exercises) for children to earn money, controlled through parental locks.
    - Track task completion.

3. **My Goals**
   
    - Allow children to set a total savings goal.
    
    - Display progress towards goals through a pie chart.
    - New goals can only be set after completing current goals.
    
4. **Instruction Manual**
    - Detailed guide on using the software.
    - Contact information for the development team.
    - Answers to common user questions.

## Configuration

All input and output data are stored in CSV file format.

## Development Process

### Agile Methods

The project follows Agile development methods, including the following phases:

1. **Requirement Gathering and Analysis**: Determine actual requirements through requirement-gathering techniques and prioritize features based on ease of implementation and meeting requirements.
2. **Design**: Design a flexible and extensible system architecture to adapt to future changes.
3. **Iterative Development**: Implement and test core functions in each iteration, extending functionalities in subsequent iterations.
4. **Testing**: Conduct unit and integration testing for each iteration to ensure stability and reliability.

### Milestones

####  Iteration 1 (April 1st - April 12th)

- **Objective:** Establish the foundational features of the Virtual Bank application.
- **Milestones:**
  - User Registration and Login
  - Current/Savings Account Creation
  - Account Freezing/Unfreezing
  - Account Cancelling
  - Balance Inquiry
  - Deposit and Withdrawal Management
  - Account Transfer
- **Tasks:**
  - Develop user interfaces for registration, login, and home page.
  - Implement core functionalities for managing accounts, including freezing, unfreezing, and cancelling accounts.
  - Ensure seamless deposit, withdrawal, and transfer processes.
  - Conduct unit testing to validate individual components.
- **Outcome:** Working Software v1 with basic account management capabilities.

#### Iteration 2 (April 12th - April 26th)

- **Objective:** Expand the application with task and goal management features.
- **Milestones:**
  - Task Setting, Recording, Rewards, and History
  - Goal Setting, Recording, and Feedback
- **Tasks:**
  - Design and develop interfaces for task and goal management.
  - Implement backend logic for task tracking and reward distribution.
  - Integrate goal management functionalities, enabling users to set and monitor financial goals.
  - Perform integration testing to ensure smooth interaction between modules.
- **Outcome:** Working Software v2 with integrated task and goal management systems.

#### Iteration 3 (April 26th - May 10th)

- **Objective:** Introduce child lock and enhance system security.
- **Milestones:**
  - Child Lock Implementation
  - Enhanced Account Security Features
- **Tasks:**
  - Develop child lock functionality, including interface and control mechanisms.
  - Implement additional security measures, such as Two-Factor Authentication (2FA).
  - Link child lock settings to task and goal management features.
  - Conduct usability testing to ensure user-friendly interfaces and interactions.
- **Outcome:** Working Software v3 with advanced security features and child lock integration.

#### Iteration 4 (May 10th - May 19th)

- **Objective:** Finalize support features and prepare for project closure.
- **Milestones:**
  - Support System: Feature Introduction, FAQ, and Contact Us
- **Tasks:**
  - Develop user support interfaces, including a comprehensive FAQ section and contact form.
  - Integrate support features into the main application.
  - Conduct system testing to verify the functionality and reliability of the support features.
- **Outcome:** Working Software v4 with complete support and assistance functionalities.

#### Iteration 5 (May 19th - May 27th)

- **Objective:** Documentation and final preparation for submission.
- **Milestones:**
  - Complete Project Documentation
  - Finalize User Manual, Readme, and Javadoc
- **Tasks:**
  - Compile and organize all project documentation, ensuring thorough coverage of all features and functionalities.
  - Finalize the user manual, providing clear instructions for all application features.
  - Generate Javadoc for all code components, ensuring comprehensive developer documentation.
  - Perform final testing to ensure all components are functioning as expected.
- **Outcome:** Submission-ready application with complete documentation.

## API Documentation

Detailed API documentation is available in the `docs` directory of this repository. It includes descriptions of all endpoints, request/response formats, and examples.

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Acknowledgments

We would like to thank everyone who contributed to and used this project. Special thanks to our development team for their hard work and dedication, which made this project possible.