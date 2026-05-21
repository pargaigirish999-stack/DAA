class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        nums=nums1+nums2
        if len(nums)==0:
            return 0.0

        nums.sort()
        n=len(nums)

        if n%2==0:
            return (nums[n//2-1]+nums[n//2])/2.0   
        else:
            return float(nums[n//2])

    
        
                
                