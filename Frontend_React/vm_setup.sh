#!/bin/bash
#Update the packeg and install git 
sudo apt update
sudo apt install git
# Clone an git Repo 
git clone https://github.com/PraveenPatidar7879/CURD_BASE_APPLICATION.git

/CURD_BASE_APPLICATION/Frontend_React

# Install nvm (Node Version Manager)
echo "Installing nvm..."
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash

# Load nvm
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"

# Install a specific version of Node.js
NODE_VERSION="14.21.3"
echo "Installing Node.js version $NODE_VERSION..."
nvm install $NODE_VERSION

# Use the installed version
nvm use $NODE_VERSION

# Set it as the default version
nvm alias default $NODE_VERSION

# Verify the installation
echo "Verifying Node.js version..."
node -v

