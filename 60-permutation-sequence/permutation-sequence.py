class Solution(object):
    def getPermutation(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """
        numbers = [str(i) for i in range(1, n + 1)]
        result = []

        # Calculate factorials
        factorial = [1] * n
        for i in range(1, n):
            factorial[i] = factorial[i - 1] * i

        k -= 1  # Convert to 0-based index

        for i in range(n, 0, -1):
            index = k // factorial[i - 1]
            result.append(numbers.pop(index))
            k %= factorial[i - 1]

        return "".join(result)
        