from abc import ABC, abstractmethod
from enum import Enum

class Color (Enum):
    black = 1
    blue = 2
    red = 3

class Shape(ABC):

    @abstractmethod
    def area(self):
        pass

class Rectangle(Shape):

    def __init__(self,x,y):
        self.l = x
        self.b = y
    
    def area(self):
        return self.l*self.b

class Circle(Shape):

    def __init__(self,x):
        self.r=x
    
    def area(self):
        return ((self.r)*(self.r))*3.14




