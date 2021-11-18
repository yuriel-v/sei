# Simply updates the YAML POM and rewrites it to XML.
# Main reason is because VSCode relies on the XML one to resolve imports. The YAML POM is for our convenience.
mvn io.takari.polyglot:polyglot-translate-plugin:translate -Dinput=pom.yml -Doutput=pom.xml
