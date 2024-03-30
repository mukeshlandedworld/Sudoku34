# Use an Nginx web server as a base image
FROM nginx:alpine

# Copy the HTML, CSS, and JavaScript files into the container
COPY index.html /usr/share/nginx/html/index.html
# Add other static files as needed

# Expose port 80
EXPOSE 80

# Command to start the Nginx web server
CMD ["nginx", "-g", "daemon off;"]
