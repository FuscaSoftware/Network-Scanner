package network.scanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for managing IP-Objects
 *
 * @author Swen Kalski
 *
 */
public class IPAddress extends Observable {

    private String host;
    private boolean reachable;

    public IPAddress(String host) {
        this.host = host;
    }

    /**
     * Returns the Hostname
     *
     * @return String hostname/IP-Address
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Set the Status of the IP-Address, if reachable or not
     *
     * @param reachable boolean true if reachable else false
     */
    public void setStatus(boolean reachable) {
        this.reachable = reachable;
        setChanged();
        notifyObservers(this);
    }

    /**
     * Shows if IP-Adress is reachable in local Network
     *
     * @return true if reachable
     */
    public boolean getStatus() {
        return this.reachable;
    }

    public boolean equals(Object o) {
        if (o instanceof IPAddress) {
            return getHost().equals(((IPAddress) o).getHost());
        }
        return false;
    }

    public String toString() {
        InetAddress currentIP = null;
        try {
            currentIP = InetAddress.getByName(this.getHost());
        } catch (UnknownHostException ex) {
            Logger.getLogger(IPAddress.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  this.getHost() + " is reachable: " + this.getStatus() 
                + " 80 (" + this.available(80 , currentIP) + ")"
                + " 8080 (" + this.available(8080 , currentIP) + ")"
                + " 445 (" + this.available(445 , currentIP) + ")"
                + " 3306 (" + this.available(3306 , currentIP) + ")";
    }

    public String available(int port, InetAddress IPAddress) {
        Socket s = null;
        try {
            s = new Socket(IPAddress, port);
            return "-";
        } catch (IOException e) {
            return "+";
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    throw new RuntimeException("You should handle this error.", e);
                }
            }
        }
    }

}
