package io.baato.osmosis.bumpids;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.openstreetmap.osmosis.core.container.v0_6.BoundContainer;
import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.container.v0_6.EntityProcessor;
import org.openstreetmap.osmosis.core.container.v0_6.NodeContainer;
import org.openstreetmap.osmosis.core.container.v0_6.RelationContainer;
import org.openstreetmap.osmosis.core.container.v0_6.WayContainer;
import org.openstreetmap.osmosis.core.domain.v0_6.CommonEntityData;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.RelationMember;
import org.openstreetmap.osmosis.core.domain.v0_6.Tag;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.domain.v0_6.WayNode;
import org.openstreetmap.osmosis.core.task.v0_6.Sink;
import org.openstreetmap.osmosis.core.task.v0_6.SinkSource;

public class BumpIdTask implements SinkSource, EntityProcessor {

	private Sink sink;
	private long bumpBy;

	public BumpIdTask(long bumpBy) {
		// TODO Auto-generated constructor stub
		this.bumpBy = bumpBy;
	}

	public void process(EntityContainer entityContainer) {
		// TODO Auto-generated method stub
		entityContainer.process(this);

	}

	public void initialize(Map<String, Object> metaData) {
		// TODO Auto-generated method stub

	}

	public void complete() {
		// TODO Auto-generated method stub
		sink.complete();

	}

	public void close() {
		// TODO Auto-generated method stub
		sink.close();
	}

	public void setSink(Sink sink) {
		this.sink = sink;
	}

	public void process(BoundContainer bound) {
		sink.process(bound);
	}

	public void process(NodeContainer node) {
		double lat = node.getEntity().getLatitude();
		double lon = node.getEntity().getLongitude();

		Collection<Tag> nodeTags = node.getEntity().getTags();

		CommonEntityData nodeCed = new CommonEntityData(node.getEntity().getId() + this.bumpBy,
				node.getEntity().getVersion(), node.getEntity().getTimestamp(), node.getEntity().getUser(),
				node.getEntity().getChangesetId(), nodeTags);

		sink.process(new NodeContainer(new Node(nodeCed, lat, lon)));

	}

	public void process(WayContainer way) {
		List<WayNode> wayNodes = way.getEntity().getWayNodes();
		Collection<Tag> wayTags = way.getEntity().getTags();

		CommonEntityData wayCed = new CommonEntityData(way.getEntity().getId() + this.bumpBy,
				way.getEntity().getVersion(), way.getEntity().getTimestamp(), way.getEntity().getUser(),
				way.getEntity().getChangesetId(), wayTags);

		List<WayNode> updatedWayNodes = new ArrayList<WayNode>();

		for (WayNode wayNode : wayNodes) {
			if (wayNode.getClass().equals(Node.class)) {
				updatedWayNodes.add(
						new WayNode(wayNode.getNodeId() + this.bumpBy, wayNode.getLatitude(), wayNode.getLongitude()));
			} else {
				updatedWayNodes.add(new WayNode(wayNode.getNodeId() + this.bumpBy));
			}
		}

		sink.process(new WayContainer(new Way(wayCed, updatedWayNodes)));

	}

	public void process(RelationContainer relation) {
		// TODO Auto-generated method stub
		List<RelationMember> relationMembers = relation.getEntity().getMembers();
		Collection<Tag> relationTags = relation.getEntity().getTags();

		CommonEntityData relationCed = new CommonEntityData(relation.getEntity().getId() + this.bumpBy,
				relation.getEntity().getVersion(), relation.getEntity().getTimestamp(), relation.getEntity().getUser(),
				relation.getEntity().getChangesetId(), relationTags);

		List<RelationMember> updatedRelationMembers = new ArrayList<RelationMember>();
		for (RelationMember relationMember : relationMembers) {
			updatedRelationMembers.add(new RelationMember(relationMember.getMemberId() + this.bumpBy,
					relationMember.getMemberType(), relationMember.getMemberRole()));
		}

		sink.process(new RelationContainer(new Relation(relationCed, updatedRelationMembers)));

	}

}
