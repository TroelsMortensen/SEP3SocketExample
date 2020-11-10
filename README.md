# SEP3SocketExample

The purpose of this repo is to give examples of how to communicate between Java and C#.
I have made a Java project and a C# project. I recommend going for version 2, see below.
In each project is a client class and a server class.
Both the C# and Java client is able to send to and receive from the Java and the C# server. So:

* Java <-> Java
* Java <-> C#
* C# <-> Java
* C# <-> C#

# H1 Version 1
This can be found in folders:
* CSharpSide/SocketClient
* CSharpSide/SocketServer
* JavaSide/SocketClient
* JavaSide/SocketServer.

I'm currently using sockets on both sides, it looks very low level, with some binary hoop jumping. 

# H1 Version 2 is up.
It is simpler, just sends byte arrays without the aforementioned binary hoop jumping. Also seem to work in all language-to-language cases. On C# side now using TcpClient and TcpListener.
Find this project in folders:

* CSharpSide/ClientVersion2
* CSharpSide/ServerVersion2
* JavaSide/Version2
