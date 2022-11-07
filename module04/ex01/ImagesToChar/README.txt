# Run the compiler
javac -d target src/java/edu/school21/printer/*/*.java

# Copy directory with resources
cp -R ./src/resources ./target/

# Create jar archive
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target/ .

chmod 777 ./target/images-to-chars-printer.jar

# Run the application
java -jar ./target/images-to-chars-printer.jar . 0