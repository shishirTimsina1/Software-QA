#include <stdio.h>
#include <pthread.h>

int shared = 0;
pthread_mutex_t lock;

void *add(void *unused) {
  for(int i=0; i < 1000000; i++) { 
    pthread_mutex_lock(&lock);
    //pthread lock
    shared++;
    pthread_mutex_unlock(&lock);
    //pthread unlock
  }
  return NULL;
}

int main() {
  pthread_t t;
  // Child thread starts running add
  pthread_create(&t, NULL, add, NULL);
  if (pthread_mutex_init(&lock, NULL) != 0) {
        printf("\n mutex init has failed\n");
        return 1;
    }
  // Main thread starts running add
  add(NULL);
  // Wait until child thread t terminates
  pthread_join(t, NULL);
  printf("shared=%d\n", shared);
  return 0;
}
