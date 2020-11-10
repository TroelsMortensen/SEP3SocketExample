using System;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace ServerVersion2
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Starting server..");

            IPAddress ip = IPAddress.Parse("127.0.0.1");
            TcpListener listener = new TcpListener(ip, 2910);
            listener.Start();

            Console.WriteLine("Server started..");

            TcpClient client = listener.AcceptTcpClient();

            Console.WriteLine("Client connected");
            NetworkStream stream = client.GetStream();

            // read
            byte[] dataFromClient = new byte[1024];
            int bytesRead = stream.Read(dataFromClient, 0, dataFromClient.Length);
            string s = Encoding.ASCII.GetString(dataFromClient, 0, bytesRead);
            Console.WriteLine(s);

            // respond
            byte[] dataToClient = Encoding.ASCII.GetBytes($"Hello from server");
            stream.Write(dataToClient, 0, dataToClient.Length);

            // close connection
            client.Close();
        }
    }
}