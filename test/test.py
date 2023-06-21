import requests
import threading
import time
import json

API_URL = 'http://app:8080/log'
payload = {
              "id": 1,
              "unixTs": 456789643,
              "userId": 123,
              "event": "login"
          }

def send_request():
    while True:
        try:
            response = requests.post(API_URL,json = payload)
            if response.status_code == 200:
                print("Request sent successfully.")
        except requests.exceptions.RequestException as e:
            print("An error occurred: {e}")
        time.sleep(1 / 1000)

threads = []
for _ in range(10):
    thread = threading.Thread(target=send_request)
    thread.start()
    threads.append(thread)

for thread in threads:
    thread.join()
