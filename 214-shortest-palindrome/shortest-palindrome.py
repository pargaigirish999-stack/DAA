class Solution(object):
    def shortestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        rev = s[::-1]
        combined = s + "#" + rev
        
        # Build LPS array (KMP table)
        lps = [0] * len(combined)
        
        for i in range(1, len(combined)):
            j = lps[i - 1]
            
            while j > 0 and combined[i] != combined[j]:
                j = lps[j - 1]
            
            if combined[i] == combined[j]:
                j += 1
            
            lps[i] = j
        
        # Longest palindromic prefix length
        longest = lps[-1]
        
        # Add remaining reversed suffix in front
        return rev[:len(s) - longest] + s
        