# Java + Nginx + MongoDB DevOps Demo

Spring Boot 3 + Java 21 application with MongoDB, Nginx reverse proxy, Docker Compose and Jenkins pipeline.

## Run locally

```bash
docker compose up -d --build
curl http://localhost/api/tasks
curl -X POST http://localhost/api/tasks -H 'Content-Type: application/json' -d '{"title":"Learn Jenkins","completed":false}'
```

Application API is exposed through Nginx on port 80. MongoDB is internal to the Compose network.

## GitHub

```bash
git init
git add .
git commit -m "Initial Java Nginx MongoDB DevOps project"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/devops-demo.git
git push -u origin main
```

## Jenkins setup

1. Install Jenkins on an Ubuntu EC2 instance.
2. Install Java 21, Git, Docker and Docker Compose plugin.
3. Add a Jenkins credential with ID `dockerhub-creds` using Docker Hub username/password or access token.
4. Replace `YOUR_DOCKERHUB_USERNAME` in `Jenkinsfile`.
5. Create a Pipeline job and select **Pipeline script from SCM**.
6. Set the GitHub repository URL and script path `Jenkinsfile`.
7. Ensure the Jenkins user can run Docker.

> For production, use a private MongoDB service/managed MongoDB, secrets management, HTTPS termination, image scanning, and a separate deployment environment. Do not store passwords in GitHub.
