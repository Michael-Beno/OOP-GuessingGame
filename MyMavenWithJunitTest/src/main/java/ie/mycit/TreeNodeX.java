package ie.mycit;

public class TreeNodeX<K extends Comparable<K>, V> {
	// package access members
//	Node<K, V> leftNode; // left node
//	K key; // node value
//	V object;
//	Node<K, V> rightNode; // right node
//
//	// constructor initializes data and makes this a leaf node
//	public TreeNodeX(K nodeKey, V valueObject) {
//		key = nodeKey;
//		object = valueObject;
//		leftNode = rightNode = null; // node has no children
//	} // end TreeNode constructor
//
//	// locate insertion point and insert new node; ignore duplicate values
//	public void insert(K insertkey, V valueObject) {
//		// insert in left subtree
//		if (insertkey.compareTo(key) <= 0) {
//			// insert new TreeNode
//			if (leftNode == null)
//				leftNode = new TreeNode<K, V>(insertkey, valueObject);
//			else // continue traversing left subtree recursively
//				leftNode.insert(insertkey, valueObject);
//		} // end if
//			// insert in right subtree
//		else if (insertkey.compareTo(key) > 0) {
//			// insert new TreeNode
//			if (rightNode == null)
//				rightNode = new TreeNode<K, V>(insertkey, valueObject);
//			else // continue traversing right subtree recursively
//				rightNode.insert(insertkey, valueObject);
//		} // end else if
//	} // end method insert
} // end class TreeNode