# Java Object Deserializer
Is a java Deserializer! Spawn a server socket and wait for serialized java object. 
Useful during pentest to get a deeper understanding of the java target application. 
In the future, the invoke option will be improved to allow the serialized object values ​​to be tampered with.
## Build and execute

Build with:

```bash
javac org/server/DeserializerServer.java
```

Execute with:

```bash
java -cp . org.server.DeserializerServer
```

## Java deserialization vulnerability
This application use untrusted JAR and untrusted serialized object and it's vulnerable to deserialization. So run it in a sandbox env.
### Other Known Issues
There are some bug, uncaught exception and the ```invoke``` option it's not so dynamic...well it's not dynamic at all, for now!
Anyway, do not leave the server open when you do not need it!

## Authors

* **G04tb0y**
## License

This project is licensed under the GPLv3 - [www.gnu.org](http://www.gnu.org/licenses/)
