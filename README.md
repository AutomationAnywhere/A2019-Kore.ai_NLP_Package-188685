# A2019 Doctools Package
_Document classification integrated seamlessly into A2019 bots._

# WARNING: THIS IS EARLY ALPHA

## Features
 * Generates single JAR file package that is importable in to an A2019 Control Room.
 * Adds the "Document Classification" Action within A2019 task bot edit/creation with the following options -
    * Create Classifier
    * Create Label
    * Label Document
    * Train Classifier
    * Classify Document
    * Remove Document
    
    
### Building
##### ALL dependencies needed for building the JAR are included in the package.
1. Clone repo into IntelliJ.
2. Run `gradle assemble` to generate the package.json.
3. Run `gradlew shadowJar` to create the importable fat JAR within the build>libs directory.



## FAQ
### How do I use this?
1. Follow Building instructions above.
2. Install the IQBotClassifier on the system you will be running the bot on -
    1. TODO: add instructions and D/L link for classifier
3. Import the generated JAR into your A2019 CR-
    1. Log in as a CR Admin.
    2. Go to Bots>Packages.
    3. Click on "Add a Package" in the upper right corner.
    4. Browse to the generated JAR.
    5. Click "Upload Package." 
    6. Click "Accept, enable and set as default."
4. Create a bot within A2019 -
    1. TODO: add instructions for bot creation once they are finalized
