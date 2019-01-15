'''
Created on Oct 23, 2018

@author: tvandrun
'''

import unittest

from polynomial import polyn_product_brute, unify_degree_bound
from polynomial import polyn_product_dc_highlow

class TestPolynomialProduct(unittest.TestCase):
    print(polyn_product_brute([1,0,1,0], [1, 1, 0, 1]))
    
    def helpTest(self, a, b):
        ppb_res = polyn_product_brute(a, b)
        other_res = self.product_fun(a, b)
        unify_degree_bound(ppb_res, other_res)
        self.assertEqual(ppb_res, other_res)
    
    def testTwoEmpty(self) :
        self.helpTest([], [])

    def testTwoConstant(self):
        self.helpTest([3], [6])
        
    def testLinear(self):
    	self.helpTest([2,7], [2,5])

    def testLinearNegative(self):
    	self.helpTest([-8,4], [16,-2])

    def testCubic(self):
    	self.helpTest([2,7,8,1], [2,5,4,3])

    def testSeventhNegative(self):
    	self.helpTest([1,2,3,4,5,6,7,8], [-11,-12,-13,-14,-15,-16,-17,-18])