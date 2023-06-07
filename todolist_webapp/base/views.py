from typing import Any, Dict
from django.forms.models import BaseModelForm
from django.http import HttpResponse
from django.shortcuts import render
from django.views.generic.list import ListView
from django.views.generic.detail import DetailView
from django.views.generic.edit import CreateView, UpdateView, DeleteView
from django.urls import reverse_lazy

from django.contrib.auth.views import LoginView

# restrict users to access their data once logged in
from django.contrib.auth.mixins import LoginRequiredMixin

from .models import Task

# Create your views here.
class CustomLoginView(LoginView):
    template_name = 'base/login.html'
    fields = '__all__'
    redirect_authenticated_user =True

    # redirect user to task list page once logged in
    def get_success_url(self) -> str:
        return reverse_lazy('tasks')


class TaskList(LoginRequiredMixin, ListView):
    model = Task
    context_object_name = 'tasks'

    # ensure that a user can only get their own data/tasks
    def get_context_data(self, **kwargs: Any):
        context = super().get_context_data(**kwargs)

        # pull the tasks for that specific user
        context['tasks'] = context['tasks'].filter(user=self.request.user)

        # what is the count of uncomplete items
        context['count'] = context['tasks'].filter(complete=False)
        return context


class TaskDetail(LoginRequiredMixin, DetailView):
    model = Task
    context_object_name = 'task'
    template_name = 'base/task.html'


class TaskCreate(LoginRequiredMixin, CreateView):
    model = Task
    # what fields do we want to show in form 
    fields = ['title', 'description', 'complete']

    # redirects user to the task list page
    success_url = reverse_lazy('tasks')

    # set the form to the logged in user
    def form_valid(self, form):
        form.instance.user = self.request.user
        return super(TaskCreate, self).form_valid(form)


class TaskUpdate(LoginRequiredMixin, UpdateView):
    model = Task
    fields = ['title', 'description', 'complete']
    success_url = reverse_lazy('tasks')


class DeleteView(LoginRequiredMixin, DeleteView):
    model = Task
    context_object_name = 'task'
    success_url = reverse_lazy('tasks')