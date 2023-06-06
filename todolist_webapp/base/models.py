from django.db import models
from django.contrib.auth.models import User

# Create your models here. Django translate these to database tables

class Task(models.Model):
    # one-to-many relationship. one user can have multiple tasks, if user deleted, tasks are deleted
    user = models.ForeignKey(User, on_delete=models.CASCADE, null=True, blank=True)
    title = models.CharField(max_length=200)
    # gives us a text box
    description = models.TextField(null=True, blank=True)
    complete = models.BooleanField(default=False)
    # automatically take a snapshot of the current timestamp
    created = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.title
    
    class Meta:
        ordering = ['complete']