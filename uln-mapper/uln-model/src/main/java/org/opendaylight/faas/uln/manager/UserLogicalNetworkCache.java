/*
 * Copyright (c) 2015 Huawei Technologies and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.faas.uln.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.opendaylight.yang.gen.v1.urn.opendaylight.faas.logical.faas.common.rev151013.Uuid;
import org.opendaylight.yang.gen.v1.urn.opendaylight.faas.logical.faas.edges.rev151013.edges.container.edges.Edge;
import org.opendaylight.yang.gen.v1.urn.opendaylight.faas.logical.faas.endpoints.locations.rev151013.endpoints.locations.container.endpoints.locations.EndpointLocation;
import org.opendaylight.yang.gen.v1.urn.opendaylight.faas.logical.faas.logical.routers.rev151013.logical.routers.container.logical.routers.LogicalRouter;
import org.opendaylight.yang.gen.v1.urn.opendaylight.faas.logical.faas.logical.switches.rev151013.logical.switches.container.logical.switches.LogicalSwitch;
import org.opendaylight.yang.gen.v1.urn.opendaylight.faas.logical.faas.ports.rev151013.PortLocationAttributes.LocationType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.faas.logical.faas.ports.rev151013.ports.container.ports.Port;
import org.opendaylight.yang.gen.v1.urn.opendaylight.faas.logical.faas.security.rules.rev151013.security.rule.groups.attributes.security.rule.groups.container.SecurityRuleGroups;
import org.opendaylight.yang.gen.v1.urn.opendaylight.faas.logical.faas.subnets.rev151013.subnets.container.subnets.Subnet;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserLogicalNetworkCache {

    private static final Logger LOG = LoggerFactory.getLogger(UserLogicalNetworkCache.class);

    private Uuid tenantId;

    private Map<Uuid, LogicalSwitchMappingInfo> lswStore;
    private Map<Uuid, LogicalRouterMappingInfo> lrStore;
    private Map<Uuid, SecurityRuleGroupsMappingInfo> securityRuleGroupsStore;
    private Map<Uuid, SubnetMappingInfo> subnetStore;
    private Map<Uuid, PortMappingInfo> portStore;
    private Map<Uuid, EdgeMappingInfo> edgeStore;
    private Map<Uuid, EndpointLocationMappingInfo> epLocationStore;

    public UserLogicalNetworkCache(Uuid tenantId) {
        super();
        this.setTenantId(tenantId);
        lswStore = new HashMap<Uuid, LogicalSwitchMappingInfo>();
        lrStore = new HashMap<Uuid, LogicalRouterMappingInfo>();
        securityRuleGroupsStore = new HashMap<Uuid, SecurityRuleGroupsMappingInfo>();
        subnetStore = new HashMap<Uuid, SubnetMappingInfo>();
        portStore = new HashMap<Uuid, PortMappingInfo>();
        edgeStore = new HashMap<Uuid, EdgeMappingInfo>();
        epLocationStore = new HashMap<Uuid, EndpointLocationMappingInfo>();
    }

    public Uuid getTenantId() {
        return tenantId;
    }

    public void setTenantId(Uuid tenantId) {
        this.tenantId = tenantId;
    }

    public boolean isLswAlreadyCached(LogicalSwitch lsw) {
        boolean found = false;

        Uuid lswId = lsw.getUuid();
        if (this.lswStore.get(lswId) != null) {
            found = true;
        }

        return found;
    }

    public boolean isLrAlreadyCached(LogicalRouter lr) {
        boolean found = false;

        Uuid lrId = lr.getUuid();
        if (this.lrStore.get(lrId) != null) {
            found = true;
        }

        return found;
    }

    public void markLswAsRendered(LogicalSwitch lsw, NodeId renderedLswId) {
        Uuid lswId = lsw.getUuid();
        this.lswStore.get(lswId).markAsRendered(renderedLswId);
    }

    public void markLrAsRendered(LogicalRouter lr, NodeId renderedLrId) {
        Uuid lrId = lr.getUuid();
        this.lrStore.get(lrId).markAsRendered(renderedLrId);
    }

    public boolean isSecurityRuleGroupsAlreadyCached(SecurityRuleGroups ruleGroups) {
        boolean found = false;

        Uuid ruleGroupsId = ruleGroups.getUuid();
        if (this.securityRuleGroupsStore.get(ruleGroupsId) != null) {
            found = true;
        }

        return found;
    }

    public void markSecurityRuleGroupsAsRendered(SecurityRuleGroups ruleGroups) {
        Uuid ruleGroupsId = ruleGroups.getUuid();
        this.securityRuleGroupsStore.get(ruleGroupsId).setServiceHasBeenRendered(true);
    }

    public boolean isSubnetAlreadyCached(Subnet subnet) {
        boolean found = false;

        Uuid subnetId = subnet.getUuid();
        if (this.subnetStore.get(subnetId) != null) {
            found = true;
        }

        return found;
    }

    public void markSubnetAsRendered(Subnet subnet) {
        Uuid subnetId = subnet.getUuid();
        this.subnetStore.get(subnetId).setServiceHasBeenRendered(true);
    }

    public boolean isPortAlreadyCached(Port port) {
        boolean found = false;

        Uuid portId = port.getUuid();
        if (this.portStore.get(portId) != null) {
            found = true;
        }

        return found;
    }

    public void markPortAsRendered(Port port) {
        Uuid portId = port.getUuid();
        this.portStore.get(portId).setServiceHasBeenRendered(true);
    }

    public boolean isEdgeAlreadyCached(Edge edge) {
        boolean found = false;

        Uuid edgeId = edge.getUuid();
        if (this.edgeStore.get(edgeId) != null) {
            found = true;
        }

        return found;
    }

    public void markEdgeAsRendered(Edge edge) {
        Uuid edgeId = edge.getUuid();
        this.edgeStore.get(edgeId).setServiceHasBeenRendered(true);
    }

    public boolean isEpLocationAlreadyCached(EndpointLocation epLocation) {
        boolean found = false;

        Uuid epLocationId = epLocation.getUuid();
        if (this.epLocationStore.get(epLocationId) != null) {
            found = true;
        }

        return found;

    }

    public void markEpLocationAsRendered(EndpointLocation epLocation,
            org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Uuid renderedEpId) {
        Uuid epLocationId = epLocation.getUuid();
        this.epLocationStore.get(epLocationId).setRenderedDeviceId(renderedEpId);
        this.epLocationStore.get(epLocationId).setServiceHasBeenRendered(true);
    }

    public void cacheLsw(LogicalSwitch lsw) {
        if (this.isLswAlreadyCached(lsw) == true) {
            return;
        }

        this.lswStore.put(lsw.getUuid(), new LogicalSwitchMappingInfo(lsw));
    }

    public void cacheLr(LogicalRouter lr) {
        if (this.isLrAlreadyCached(lr) == true) {
            return;
        }

        this.lrStore.put(lr.getUuid(), new LogicalRouterMappingInfo(lr));
    }

    public void cacheSecurityRuleGroups(SecurityRuleGroups ruleGroups) {
        if (this.isSecurityRuleGroupsAlreadyCached(ruleGroups) == true) {
            return;
        }

        this.securityRuleGroupsStore.put(ruleGroups.getUuid(), new SecurityRuleGroupsMappingInfo(ruleGroups));
    }

    public void cacheSubnet(Subnet subnet) {
        if (this.isSubnetAlreadyCached(subnet) == true) {
            return;
        }

        this.subnetStore.put(subnet.getUuid(), new SubnetMappingInfo(subnet));
    }

    public void cachePort(Port port) {
        if (this.isPortAlreadyCached(port) == true) {
            return;
        }

        this.portStore.put(port.getUuid(), new PortMappingInfo(port));
    }

    public void cacheEdge(Edge edge) {
        if (this.isEdgeAlreadyCached(edge) == true) {
            return;
        }

        this.edgeStore.put(edge.getUuid(), new EdgeMappingInfo(edge));
    }

    public void cacheEpLocation(EndpointLocation epLocation) {
        if (this.isEpLocationAlreadyCached(epLocation) == true) {
            return;
        }

        this.epLocationStore.put(epLocation.getUuid(), new EndpointLocationMappingInfo(epLocation));
    }

    public boolean isLswRendered(LogicalSwitch lsw) {
        if (this.isLswAlreadyCached(lsw) == false) {
            return false;
        }
        return this.lswStore.get(lsw.getUuid()).hasServiceBeenRendered();
    }

    public boolean isLrRendered(LogicalRouter lr) {
        if (this.isLrAlreadyCached(lr) == false) {
            return false;
        }
        return this.lrStore.get(lr.getUuid()).hasServiceBeenRendered();
    }

    public boolean isSubnetRendered(Subnet subnet) {
        if (this.isSubnetAlreadyCached(subnet) == false) {
            return false;
        }
        return this.subnetStore.get(subnet.getUuid()).hasServiceBeenRendered();
    }

    public boolean isPortRendered(Port port) {
        if (this.isPortAlreadyCached(port) == false) {
            return false;
        }
        return this.portStore.get(port.getUuid()).hasServiceBeenRendered();
    }

    public boolean isSecurityRuleGroupsRendered(SecurityRuleGroups ruleGroups) {
        if (this.isSecurityRuleGroupsAlreadyCached(ruleGroups) == false) {
            return false;
        }
        return this.securityRuleGroupsStore.get(ruleGroups.getUuid()).hasServiceBeenRendered();
    }

    public boolean isEdgeRendered(Edge edge) {
        if (this.isEdgeAlreadyCached(edge) == false) {
            return false;
        }
        return this.edgeStore.get(edge.getUuid()).hasServiceBeenRendered();
    }

    public boolean isEpLocationRendered(EndpointLocation epLocation) {
        if (this.isEpLocationAlreadyCached(epLocation) == false) {
            return false;
        }
        return this.epLocationStore.get(epLocation.getUuid()).hasServiceBeenRendered();
    }

    /*
     * Find edge that connects the given EP with its belonging subnet
     */
    public EdgeMappingInfo findEpLocationSubnetEdge(EndpointLocation epLocation) {
        EdgeMappingInfo edge = null;

        Uuid epPortId = epLocation.getPort();
        PortMappingInfo epPort = this.portStore.get(epPortId);
        if (epPort == null) {
            return null;
        }

        Uuid edgeId = epPort.getPort().getEdgeId();
        edge = this.edgeStore.get(edgeId);

        return edge;
    }

    /*
     * Given an edge and one port, find the other port.
     */
    public PortMappingInfo findOtherPortInEdge(EdgeMappingInfo epEdge, Uuid epPortId) {
        Uuid leftPortId = epEdge.getEdge().getLeftPortId();
        Uuid rightPortId = epEdge.getEdge().getRightPortId();

        Uuid otherPortId;
        if (leftPortId.equals(epPortId) == true) {
            otherPortId = rightPortId;
        } else if (rightPortId.equals(epPortId) == true) {
            otherPortId = leftPortId;
        } else {
            LOG.error("FABMGR: ERROR: findOtherPortInEdge: port id is wrong: ep={}, left={}, right={}",
                    epPortId.getValue(), leftPortId.getValue(), rightPortId.getValue());
            return null;
        }

        PortMappingInfo otherPort = this.portStore.get(otherPortId);

        return otherPort;
    }

    /*
     * Given a port, find the subnet to which this port belongs.
     */
    public SubnetMappingInfo findSubnetFromItsPort(PortMappingInfo subnetPort) {
        LocationType portLocationType = subnetPort.getPort().getLocationType();

        if (portLocationType != LocationType.SubnetType) {
            LOG.error("FABMGR: ERROR: wrong port type: {}", portLocationType.name());
            return null;
        }

        Uuid subnetId = subnetPort.getPort().getLocationId();
        SubnetMappingInfo subnet = this.subnetStore.get(subnetId);

        return subnet;
    }

    /*
     * Given a subnet, find the edge that connects this subnet with
     * a logical switch.
     */
    public EdgeMappingInfo findSubnetLswEdge(SubnetMappingInfo subnet) {
        EdgeMappingInfo subnetLswEdge = null;

        Uuid subnetId = subnet.getSubnet().getUuid();

        for (Entry<Uuid, EdgeMappingInfo> entry : this.edgeStore.entrySet()) {
            EdgeMappingInfo edge = entry.getValue();
            PortMappingInfo subnetPort = this.edgeHasSubnetPort(edge, subnetId);
            if (subnetPort != null) {
                PortMappingInfo otherPort = this.findOtherPortInEdge(edge, subnetPort.getPort().getUuid());
                if (otherPort != null) {
                    LocationType portType = otherPort.getPort().getLocationType();
                    if (portType == LocationType.SwitchType) {
                        subnetLswEdge = edge;
                    }
                }
            }
        }

        return subnetLswEdge;
    }

    public PortMappingInfo edgeHasSubnetPort(EdgeMappingInfo edge, Uuid subnetId) {
        PortMappingInfo subnetPort = null;
        Uuid leftPortId = edge.getEdge().getLeftPortId();
        Uuid rightPortId = edge.getEdge().getRightPortId();

        if (this.portBelongsToSubnet(leftPortId, subnetId) == true) {
            subnetPort = this.portStore.get(leftPortId);
        } else if (this.portBelongsToSubnet(rightPortId, subnetId) == true) {
            subnetPort = this.portStore.get(rightPortId);
        }

        return subnetPort;

    }

    public boolean portBelongsToSubnet(Uuid portId, Uuid subnetId) {
        PortMappingInfo port = this.portStore.get(portId);

        if (this.portIsSubnetType(portId) == false) {
            return false;
        }

        if (port.getPort().getLocationId().equals(subnetId) == false) {
            return false;
        }

        return true;
    }

    /*
     * Given an edge which connects a subnet and a LSW, find the subnet port
     * on edge.
     */
    public PortMappingInfo findSubnetPortOnEdge(EdgeMappingInfo edge) {
        Uuid leftPortId = edge.getEdge().getLeftPortId();
        if (this.portIsSubnetType(leftPortId) == true) {
            return this.portStore.get(leftPortId);
        }

        Uuid rightPortId = edge.getEdge().getRightPortId();
        if (this.portIsSubnetType(rightPortId) == true) {
            return this.portStore.get(rightPortId);
        }

        return null;
    }

    public boolean portIsSubnetType(Uuid portId) {
        return this.portIsType(portId, LocationType.SubnetType);
    }

    public boolean portIsLswType(Uuid portId) {
        return this.portIsType(portId, LocationType.SwitchType);
    }

    public boolean portIsType(Uuid portId, LocationType portType) {
        PortMappingInfo port = this.portStore.get(portId);

        if (port == null) {
            return false;
        }

        LocationType myType = port.getPort().getLocationType();
        if (myType == portType) {
            return false;
        }

        return true;
    }

    /*
     * Given an port on LSW, find the LSW.
     */
    public LogicalSwitchMappingInfo findLswFromItsPort(PortMappingInfo port) {
        Uuid portId = port.getPort().getUuid();
        if (this.portIsLswType(portId) == false) {
            return null;
        }

        Uuid lswId = port.getPort().getLocationId();

        return this.lswStore.get(lswId);
    }

    public PortMappingInfo findEpPortFromEpLocation(EndpointLocation epLocation) {
        Uuid epPortId = epLocation.getPort();
        return this.portStore.get(epPortId);
    }

}
