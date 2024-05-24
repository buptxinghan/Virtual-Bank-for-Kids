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

- Java Development Kit (JDK) 22 or higher
- Apache Maven

### Steps

1. Clone the repository:
    ```bash
    git clone https://github.com/xxx/virtual-bank-kids.git
    cd virtual-bank-kids
    ```

2. Build the project:
    ```bash
    
    ```

3. Run the application:
    ```bash
    
    ```

## Usage

After running the application, users can interact through a graphical user interface (GUI). Upon logging in, the interface is divided into four sections: My Account, My Tasks, My Goals, and Instruction Manual.

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

- **Product Backlog and Prototype**: April 15, 2024
- **Final Submission**: Report and Software: May 27, 2024
- **Demonstration**: May 27-31, 2024

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