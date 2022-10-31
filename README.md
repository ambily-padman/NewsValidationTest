~~~~~~~~~~****************~~~~~~~~~~
# NewsValidationTest

A machine learning approach  is desirable for this project, as it involve comparing some headline against a list of other headlines. But the timeline is limited, the easiest approach I could think of is to select a tool that can do a semantic similarity check. So that we can compare the semantic similarity of two news. I am selected NLPCloud here. There are plenty of other tools available, but I feel this tool is more straint forward and give more features when we use the free version.

#### How it works 
NLP cloud will give a score when we comapre two sentences . Here  in this project when the simailarity score greater than or equel to 0.5 is considered as similar  news. If more than 2 different sources give positive score on similarity check then that news will be considered as valid news.or else fake.
But as we are using free version of the tool it have a limitaion of getting timeout after 3 consecutive call. Because of that sometime we get fake news alert eveenthought the news is not fake. But the full priced version will fix this issue






## Features


* Code reusability
* Cucumber Test Report
* Pagefactory design pattern
* Rest Assured for API request

## Installation

### Prerequisites
You’ll need to install:

Java 11

Maven latest https://maven.apache.org/download.cgi

Latest IntelliJ or any IDE of your choice and then install the latest cucumber plugin.

Install the latest webdriver or the one that matches your current chrome browser https://chromedriver.chromium.org/downloads
## Setting up Locally

Clone the project

```bash
 git@github.com:ambily-padman/NewsValidationTest.git
```

Go to the project directory

```bash
    mvn clean build
```

if you are using Intellij you can reload the project to import the dependency
## Running Tests

### Running Test Locally

* There is a property folder you can see in (src/main/resources/config.properties)[click here](https://github.com/ambily-padman/NewsValidationTest/blob/master/src/main/resources/config.properties)
 
* As mentioned earlier you need to download the driver and provide the location as the value of 'driver.location' 

* Also you can decide which browser 

* And can select what is the wait time. Depending on the pageload time you experience, you can increase or decrease the 'selenium.timeout'

* The NLP Cloud API token can be expired after certain period of time , If this happens the test will not work, but you can get new token by clciking the link [here](https://nlpcloud.com/home/) and add it to "api.token" in config file

At a time we can only run one scenario at a time because the free version of NLP cloud API will get time out after 3 consecutive request. In order to get this working we need to upgrade the tool.

#### Different options for running tests: 


1 ) Right click the scenario name and you will get the cucmber run option if you already installed cucumber plugin in your IDE

2 )  Right click the feature file and run

3 ) Right click the TestRunner class you wills see runner class in ('src/test/java/com/mailonline/runner/TestRunner.java' ) [click here](https://github.com/ambily-padman/NewsValidationTest/blob/master/src/test/java/com/news/runner/TestRunner.java)


4 ) You can run from command line
```bash
mvn test -Dcucumber.filter.tags="@test"
```

## Test Report

You will see the test report under the folder target , which will be auto created once you run the test from command line or from TestRunner class. You can right click and open the target/cucumber.html in any browser. You will see the detailed report there.


## Optimizations


Because of the limited time I cannot able to able to include following feature,which can be added for future enchancement


1 I am using nlpcloud for semantic similarity check , but this is a paid tool, So we cannot able to make too many request with in certain time limit. So there is a possibility  that we may get news validity check fail when this timeout happens even though news are not fake.

2 Screenshot taken when the test fails

3 Rerun feature when test fails

4 Parallel run is not possible if we use paid NLP Cloud.

5 PageFactory initElements(SearchContext searchContext, Class pageClassToProxy) could have been added to avoid the use of List of elements

6 Some more refactoring can be possible.

7 CI/CD set up

8 Loging


## Related

Soem references :

[Fake New detection](https://arxiv.org/pdf/1708.01967.pdf)

[ NLP Cloud Documentation ](https://docs.nlpcloud.com/#introduction)


