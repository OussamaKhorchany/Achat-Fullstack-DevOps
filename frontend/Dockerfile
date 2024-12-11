# Stage 1: Build the Angular app
FROM node:16 AS build

# Set working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the entire Angular app source code
COPY . .

# Build the Angular app
RUN npm run build --prod

# Stage 2: Serve the Angular app with Nginx
FROM nginx:alpine

# Copy the built Angular app to the Nginx HTML directory
COPY --from=build /app/dist/achat /usr/share/nginx/html

# Expose port 80 for the web server
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]


