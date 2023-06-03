# Network-Routing-Simulation
Repository made in java for simple network routing
This is an oriented and weighted graph school project.
The code read the file Node.txt.
The structure of file is like this :
1. The first line is a list of node separated by comma :
  Example : 192.0.0.1, ..., ..., 
2. The other lines are just the relation between each node :
    - The structure is : From Node ... To Node ... Weight ... Reachable ( true or false )
    - Example : 192.0.0.1 192.0.0.2 10 true

3 - To run it use :
``` Powershell
    java main.Main Node.txt
```
