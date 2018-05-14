# txs
This applications allows the user to handle their financial transactions. 

### How to run

#### Using maven

1. Make sure you have installed Maven. You can see how install it from [here](https://maven.apache.org/)
2. Run `mvn clean package` at the root of the project
3. Run `java -jar target/app-1.jar <options>` to interact with the application

#### Using javac

1. Compile the source code using `javac`
2. Run the Txs class providing the options you want to pass to the app

### How to use

You can run `java -jar target/app-1.jar -h` to get the user manual. 

#### Add a transaction

Run `java -jar target/app-1.jar <userId> add <transactionJson>`. 
If your json is well formed you should see a json for the transaction with an id assigned.

#### Show a transaction

Run `java -jar target/app-1.jar <userId> <transactionId>`. The param transactionId is expected to be a UUID in string format

#### Sum transactions

Run `java -jar target/app-1.jar <userId> sum`. It will calculate the sum of all the transactions associated with the given userId

#### List the transactions

Run `java -jar target/app-1.jar <userId> list`. It will list all the transactions  associated with the given userId

#### Technologies used

This application is using the following technologies/libraries

- Java 10
- Junit 4
- Mockito
- ArgParse4j
- Jackson
- Hamcrest
- Maven

#### How is this built?

The application is storing the transactions you add in the file system. 
It is mandatory to give write permissions over the running directory, otherwise the application will fail.

A `.ser` file will be created for each user id that is used in the application. 
The `.ser` extension is commonly used for saving serialized data. 
Your transactions are being hold in a map and then it's getting serialized and saved to file system. 

**Note**: You won't be able to see you transactions when opening the `ser` files because of the algorithm used by Java.

    
