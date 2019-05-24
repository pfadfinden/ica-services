package de.pfadfinden.ica;

public class IcaServer {

    public static final IcaServer BDP_QA = new IcaServer("qa.mv.meinbdp.de", "ica");
    public static final IcaServer BDP_PROD = new IcaServer("mv.meinbdp.de", "ica");

    private String host;
    private String deployment;

    public IcaServer(String host, String deployment) {
        this.host = host;
        this.deployment = deployment;
    }

    public String getHost() {
        return host;
    }
    public String getDeployment() { return deployment; }
}
