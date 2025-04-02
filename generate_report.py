import json
import os
from jinja2 import Environment, FileSystemLoader

# Load JSON reports
def load_json(file_path):
    if os.path.exists(file_path):
        with open(file_path, 'r') as f:
            return json.load(f)
    return {}

# Load data
sonarqube_data = load_json('reports/sonarqube-report.json')
owasp_data = load_json('reports/dependency-check-report.json')
trivy_data = load_json('reports/trivy-report.json')

# Prepare report data
report = {
    "sonarqube_issues": sonarqube_data.get("issues", []),
    "owasp_vulnerabilities": owasp_data.get("dependencies", []),
    "trivy_vulnerabilities": trivy_data.get("Results", [])
}

# Set up Jinja2 environment
env = Environment(loader=FileSystemLoader('.'))
template = env.get_template('report_template.html')

# Generate HTML report
with open('reports/devsecops-report.html', 'w') as f:
    f.write(template.render(report=report))

print("DevSecOps report generated at reports/devsecops-report.html")