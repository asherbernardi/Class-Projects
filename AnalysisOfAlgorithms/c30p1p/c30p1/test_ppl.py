'''
Created on Oct 23, 2018

@author: thomasvandrunen
'''

 
import unittest

from polynomial import polyn_product_linear, polyn_product_brute, unify_degree_bound
 
class TestPolynomialProductLinear(unittest.TestCase):

    def helpTest(self, a, b):
        ppb_res = polyn_product_brute(a, b)
        ppl_res = polyn_product_linear(a, b)
        unify_degree_bound(ppb_res, ppl_res)
        print(ppb_res)
        self.assertEqual(ppb_res, ppl_res)

    def testTwoEmpty(self) :
        self.helpTest([], [])

    def testTwoConstant(self):
        self.helpTest([3], [6])
        
    def testA(self):
        self.helpTest([2,7], [2,5])
        
    def testB(self):
        self.helpTest([8,1], [4,3])

    def testOct(self):
        print(polyn_product_brute([1,2,3,4], [8,7,6,5]))
        print(polyn_product_brute([1,2,3,4,5,6,7,8], [8,7,6,5,4,3,2,1]))