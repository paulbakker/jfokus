This is the code written during the Java EE JFokus talk by Paul Bakker and Bert Ertman.
You will need to setup a few things in your application server to run the example:
 * A data source named "ducttape"
 * A JMS ConnectionFactory named "jms/ConnectionFactory"
 * A JMS Topic named "topic/orders"

The example is portable and should run on any application server. You might have to change the scope of some maven dependencies to provided when running on
JBoss AS 7 however (even smaller WAR file!). During the presentation Glassfish 3.1.1 was used.

Also take a look at [JBoss Forge](http://jboss.org/forge)