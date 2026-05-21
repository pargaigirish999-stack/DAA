class Solution(object):
    def buildTree(self, inorder, postorder):
        """
        :type inorder: List[int]
        :type postorder: List[int]
        :rtype: Optional[TreeNode]
        """
        if not inorder or not postorder:
            return None

        # Map value -> index in inorder for O(1) lookup
        inorder_map = {val: i for i, val in enumerate(inorder)}

        # Start from last index of postorder
        self.post_idx = len(postorder) - 1

        def build(left, right):
            if left > right:
                return None

            root_val = postorder[self.post_idx]
            self.post_idx -= 1

            root = TreeNode(root_val)

            mid = inorder_map[root_val]

            # IMPORTANT: build right subtree first
            root.right = build(mid + 1, right)
            root.left = build(left, mid - 1)

            return root

        return build(0, len(inorder) - 1)
        