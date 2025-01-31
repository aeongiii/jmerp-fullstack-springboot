from flask import Flask, request, jsonify, send_file
import json
import os

app = Flask(__name__)

# 예측 결과 불러오기
def load_predictions():
    with open("predictions.json", "r", encoding="utf-8") as f:
        return json.load(f)

@app.route('/predict', methods=['GET'])
def predict():
    category = request.args.get('category')
    predictions = load_predictions()

    if category not in predictions:
        return jsonify({"error": "No prediction data found for this category"}), 404

    return jsonify({"category": category, "forecast": predictions[category]})

@app.route('/graph', methods=['GET'])
def get_graph():
    category = request.args.get('category')
    file_path = f'static/{category}_forecast.png'

    if not os.path.exists(file_path):
        return jsonify({"error": "Graph not found"}), 404

    return send_file(file_path, mimetype='image/png')

if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5000, debug=True)