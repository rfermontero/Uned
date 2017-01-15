rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false &
sleep 3 #Wait until rmi registry is up.
java -jar Jars/server.jar &
java -jar Jars/client.jar &
java -jar Jars/repository.jar &
