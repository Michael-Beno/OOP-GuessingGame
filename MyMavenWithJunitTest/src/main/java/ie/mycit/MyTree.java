package ie.mycit;

//class MyTree definition
public class MyTree<K extends Comparable<K>, V> {

	private Node<K, V> root;
	@SuppressWarnings("hiding")
	private class Node<K, V> {
		// package access members

		K key; // node value
		V value;
		Node<K, V> rightNode; // right node
		Node<K, V> leftNode; // left node
		// constructor initializes data and makes this a leaf node

		public Node(K nodeKey, V valueObject) {
			key = nodeKey;
			value = valueObject;
			leftNode = rightNode = null; // node has no children
		} // end TreeNode constructor
	}

	// constructor initializes an empty Tree of integers
	public MyTree() {
		root = null;
	} // end Tree no-argument constructor

	private int compare(K key1, K key2) {
		int i;
		if (key1.compareTo(key2) < 0)
			i = -1; // key1 < key2
		else if (key1.compareTo(key2) > 0)
			i = 1; // key1 > key2
		else
			i = 0; // key1 == key2
		return i;
	}

	private void add(Node<K, V> ptr, K key, V value) {
		if (compare(key, ptr.key) < 0) {
			if (ptr.leftNode == null) {
				Node<K, V> n = new Node<K, V>(key, value);
				ptr.leftNode = n;
			} else
				add(ptr.leftNode, key, value);
		} else {
			if (ptr.rightNode == null) {
				Node<K, V> n = new Node<K, V>(key, value);
				ptr.rightNode = n;
			} else
				add(ptr.rightNode, key, value);
		}
	}

	public void add(K key, V value) {
		if (root == null)
			root = new Node<K, V>(key, value);
		else
			add(root, key, value);
		size++;
	}

	private Node<K, V> search(Node<K, V> ptr, K key) {
		Node<K, V> n = null;
		int i = compare(key, ptr.key);
		if (i == 0)
			n = ptr;
		else if (i < 0) {
			if (ptr.leftNode != null)
				n = search(ptr.leftNode, key);
		} else {
			if (ptr.rightNode != null)
				n = search(ptr.rightNode, key);
		}
		return n;
	}

	public Node<K, V> search(K key) {
		Node<K, V> n;
		if (root == null)
			n = null;
		else if (compare(root.key, key) == 0)
			n = root;
		else
			n = search(root, key);
		return n;
	}

	public V getValue(K key) {
		Node<K, V> n = search(key);
		return (n == null ? null : n.value); 
	}
	private int tmp = 0;
	private String getNodeString(Node<K, V> ptr, String s) {
		// A > Z
		String temp_s = "";
		if (ptr.leftNode != null)
			temp_s += getNodeString(ptr.leftNode, s);
		//temp_s += ptr.key.toString()+"\n";
		keySet[tmp++] = ""+ptr.key;
		if (ptr.rightNode != null)
			temp_s += getNodeString(ptr.rightNode, s);
		return temp_s;
	}
	
	private String[] keySet;
	public String[] getSet() {
		keySet = new String[size()];
		if (root != null) getNodeString(root, "");
			return keySet;
	}

	/** Returns Object from keyValue */
	public V getObject(K key) {
		return searchObject(root, key);
	}
	private V searchObject(Node<K, V> node, K inputKeyVal) {
		V objectValue = null;

		if (node != null) {
			if (node.key.compareTo((K) (inputKeyVal)) == 0)
				return node.value;
			objectValue = searchObject(node.leftNode, inputKeyVal); // traverse left subtree
			if (objectValue == null) // go to right subtree if only previous is null
				objectValue = searchObject(node.rightNode, inputKeyVal); // traverse right subtree
		}
		return objectValue;
	}
	
	private int size;
	public int size() { return size; }
} // end class MyTree
