from django.shortcuts import render
from django.views.generic.list import ListView

# Create your views here.
class TaskList(ListView):
    model = Task