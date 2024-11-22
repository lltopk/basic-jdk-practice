`CopyOnWriteArrayList` 的设计允许读操作看到老副本的值，这是其写时(add/remove)复制策略的一部分。

虽然这可能会导致读操作看到不是最新状态的数据，但这种设计确保了读操作的高性能和线程安全性。对于大多数读多写少的场景，这种行为是可以接受的。

如果需要严格的读取一致性，可以考虑其他线程安全的集合实现，如 `Vector` 或 `Collections.synchronizedList`，但这些实现的性能通常不如 `CopyOnWriteArrayList`。