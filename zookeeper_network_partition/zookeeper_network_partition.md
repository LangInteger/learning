---
presentation:
  width: 1200
  height: 800
  theme: solarized.css
  enableSpeakerNotes: true
---

<!-- slide data-notes="Write your note here" -->

## Zookeeper in Network Partition

#### 2023-09-14

#### Lang Liu

<!-- slide -->

## Related Configurations

<!-- slide vertical=true -->

#### Quorum Construction

- group.x=nnnnn[:nnnnn]
- weight.x=nnnnn

<!-- slide vertical=true -->

#### Behavior Under Network Partition

- readonlymode.enabled (server side)
- canBeReadOnly (client side)

<!-- slide -->

## Conclusion

- Zookeeper always **avoids Split Brain** problem
  - with it's majority rule for quorum
- In network partition, the group with the majority nodes
  - can continue to serve read and write requests
- In network partition, the group with minority nodes
  - can **not** continue to serve write requests
  - can continue to serve read requests with client and server properly configured.
    - It is obvious that read requests in this case will return stale values.

<!-- slide -->

## Common Misunderstanding

<!-- slide vertical=true -->

#### An odd number of servers is needed for the majority rule

It is wrong. Reasons:

- with Zookeeper cluster of 5 nodes
  - the quorum with default majority rule (no weight, no group) will need 5 / 2 + 1 = 3 nodes
  - which means we can tolerate the failure of 5 - 3 = 2 nodes
- with Zookeeper cluster of 6 nodes
  - the quorum with default majority rule (no weight, no group) will need 6 / 2 + 1 = 4 nodes
  - which means we can tolerate the failure of 6 - 4 = 2 nodes
- Without improvement on failure tolerance, it does not make sense to maintain a Zookeeper cluster with an even number of ndoes.

<!-- slide -->

## The END
