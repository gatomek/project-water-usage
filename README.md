# Aplication
* http://localhost:8080/water-day-usage-bars.htm
* http://localhost:8080/water-day-usage-lines.html
* http://localhost:8080/rest/water-day-usage

# Linki 1
* https://stackoverflow.com/questions/37436927/utf-8-encoding-of-application-properties-attributes-in-spring-boot
* https://plotly.com/javascript/getting-started/
* https://www.baeldung.com/spring-mvc-static-resources
* https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#features.docker-compose
* http://localhost:8080/demon.html
* https://codeburst.io/returning-csv-content-from-an-api-in-spring-boot-63ea82bbcf0f
* https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/MediaType.html
* https://raw.githubusercontent.com/plotly/datasets/master/finance-charts-apple.csv

* https://www.baeldung.com/spring-value-annotation
* https://stackoverflow.com/questions/37436927/utf-8-encoding-of-application-properties-attributes-in-spring-boot
* https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
* https://www.baeldung.com/executable-jar-with-maven
* https://github.com/adessoSE/junit-insights
* https://docs.spring.io/spring-boot/docs/current/reference/html/test-auto-configuration.html
* https://reflectoring.io/dont-use-spring-profile-annotation/

# Linki 2
* https://github.com/johan974/spring_unittest_or_integrationtest_findbugs_jacoco/blob/master/pom.xml
* https://medium.com/@vandernobrel/running-unit-and-integration-test-separately-in-maven-a3e82d25cb7d
* https://maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html
* https://maven.apache.org/surefire/maven-failsafe-plugin/examples/inclusion-exclusion.html
* https://github.com/eugenp/tutorials/blob/master/maven-modules/maven-integration-test/pom.xml
* https://stackoverflow.com/questions/76443709/i-updated-my-maven-surefire-plugin-from-2-20-to-3-1-2-and-its-giving-me-an-error

# Release Notes
* aplikacja jako pojedynczy plik jar
* separating unit tests from integration tests
* configuration moved from application.properties to application.yml

# Backlog
* wdrożenie logowania log4j
* rekordy zamiast klas dto
* monitoring prometheus + grafana
  * ile czasu trwała generacja odpowiedzi 
* wdrożenie bibliotek *.min.js
* dokumentacja
* testy jednostkowe
* testy integracyjne
* przejście na inny sposób iteracji po systemie katalogów i plików
* aplikacja jako usługa windows - automatyczny start w tle
* przerywanie ciągłości wykresu przy braku próbek na dany dzień
* aktualizacja zależności

# Tematy rozwojowe
* integracja z Obsidian ?
* uruchomienie na Dockerze ?

# Pytania
* jak podać port przez zmienną środowiskową ?
* jak zamienić silnik Tomcata na inny serwer ?
* kaskadowe wczytywanie parametrów z plików *.properties ?

///
As already mentioned in the comments .properties files are expected to be encoded in ISO 8859-1

# Sprawdzenie strony kodowej MS Windows
## PowerShell:
[System.Text.Encoding]::Default

# Wyświetlenie bieżącego kodowania Maszyny Wirtualnej
Charset.defaultCharset().displayName()




