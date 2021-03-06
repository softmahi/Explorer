@ignore @manual

Feature: Explorer-Spark Integration
  In order to Connect to Spark and submit spark jobs
  As a Stratio Explorer User
  I want to do some request to Spark

    #JIRA - EXPLORER-132
  Scenario: Check empty fields Spark interpreter
    Given empty 'editor' field
    And Select 'Spark' interpreter
    When Click run button
    Then infinite loop


  Scenario: Test simple Spark Script

  Scenario: Test Spark Script with RDDs

  Scenario: Test Spark Streaming capabilities

  Scenario: Test Spark MLib capabilities

    #JIRA - EXPLORER-157
  Scenario: Load HDFS file
    Given a file "songs.txt" stored in HDFS
    And a val named textFile in the context with the data of "songs.txt"
    When I execute "val counts = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _).collect"
    Then I got the file data

  Scenario: Test create simple array
    Given: Create simple array with "Array(1,2,3,4,5)" command
    Then: Array created succesfully

  Scenario: Test parallelize data
    Given: Create parallelize with "sc.parallelize(data)" command
    Then: Transform created succesfully

  Scenario: Test collect data
    Given: Ckeck Collect data with "collect" command
    Then: Action works succesfully

  Scenario: Test mapping pairs
    Given: Ckeck mapping pairs of data with "map" transform
    Then: Transform created succesfully

  Scenario: Test take command
    Given: Ckeck "take" action
    Then: Action works succesfully

  Scenario: Test reduce command
    Given: Ckeck "reduce" transform
    Then: Transform created succesfully

  Scenario: Test take command
    Given: Ckeck "reduceByKey" action
    Then: Transform created succesfully

    #JIRA - EXPLORER-159
  Scenario: Test scope of the commands
    Given: Open a new notebook named "1"
    And: Select "spark" interpreter
    And: Open another new notebook named "2"
    And: Select "spark" interpreter
    When: I put "val data = Array(1, 2, 3, 4, 5)" command into notebook "1" and press run button
    And: I Execute "data.collect" command in notebook "2"
    Then: The command "data.collect" works in notebook "2"