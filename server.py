from flask import Flask, request, jsonify, render_template
from flask_cors import CORS
import requests
import sqlite3

app = Flask(__name__)
CORS(app) 

# Твои доступы
TOKEN = '8863959313:AAE3ErrOhL6AD-ZOyctSEo1ebJRxIxFuXEQ'
GROUP_ID = '-5335547750'

def init_db():
    conn = sqlite3.connect('coffee_shop.db')
    cursor = conn.cursor()
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS orders (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            details TEXT NOT NULL,
            total_price INTEGER NOT NULL,
            status TEXT DEFAULT 'Новый'
        )
    ''')
    conn.commit()
    conn.close()

init_db()

@app.route('/')
def home():
    # Вот здесь Flask ищет файл index.html внутри папки templates/
    return render_template('index.html')

@app.route('/api/order', methods=['POST'])
def new_order():
    data = request.json
    drinks = data.get('drinkName')
    price = data.get('totalPrice')
    
    conn = sqlite3.connect('coffee_shop.db')
    cursor = conn.cursor()
    cursor.execute('INSERT INTO orders (details, total_price) VALUES (?, ?)', (drinks, price))
    order_id = cursor.lastrowid 
    conn.commit()
    conn.close()
    
    text = f"⚡️ ЗАКАЗ #{order_id}\nНапиток: {drinks}\nК оплате: {price} руб."
    
    url = f"https://api.telegram.org/bot{TOKEN}/sendMessage"
    payload = {
        "chat_id": GROUP_ID,
        "text": text
    }
    requests.post(url, json=payload)
    
    return jsonify({"success": True})

if __name__ == '__main__':
    app.run(port=3000)
