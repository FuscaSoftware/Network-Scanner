# Network-Scanner
Tiny and overhead free network scanner to find used IP's.

I got several Routers at home. Most i don't forget, the IP's where anywhere at 
192.168.3.150 and 192.168.3.200. But often i have no glue where exactly.

So i coded this little scanner.

It has no foreign dependencies and run with JDK > 7.

I will add a portscan soon.
The Method is allready located at

```
public class IPAddress extends Observable {
  ...

  public String portAvailable(int port){
    ...
  }
}
```

