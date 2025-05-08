# Read Me First

To run this project you will need to run the docker-compose file to
create the database environment. Also, you will need aa Java 21 environment and maven


* The original package name 'com.wallet-service' is invalid and this project uses 'com.wallet_service' instead.

# Getting Started

### Environment Setup
* run docker-compose up
* run mvn clean install (on the project root directory)

### Reference Documentation
this application have 6 endpoints/functions, to comply the project requirements:

* [create a new wallet] POST http://localhost:8080/wallet
* [get actual balance of a wallet] GET http://localhost:8080/wallet/balance/{walletId}
* [deposit operation] POST http://localhost:8080/wallet/deposit
* [withdraw operation] POST http://localhost:8080/wallet/withdraw
* [get wallet historical balance] GET http://localhost:8080/transaction/wallet/{walletId}
* [transfer operation between wallets] POST http://localhost:8080/wallet/transfer

### Project Architecture and Implementation Decisions

In this project, I chose a simple layer separation between Service and UseCase, keeping business entity validations and handling in the Service layer while delegating operation orchestration to the UseCase layer. I aimed to maintain functionalities with minimal coupling, anticipating a scenario where the Transaction entity handling could be migrated to an independent microservice.

An example of this strategy is the transfer operation, which essentially orchestrates withdraw and deposit operations in the UseCase layer.

This is not a strict hexagonal architecture implementation, as the project's premise was to remain simple and easy to understand. For the same reason, I opted not to write unit tests, leaving it as technical debt to achieve 90% test coverage across the Controller, UseCase, and Service layers.

### Implementation Time:

1 hour for scope understanding and solution design.

9 hours for coding.
