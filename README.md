# Smart home project
## Development team
- Geoffrey Auzou
- Amandine Carlier
- Maxime Frémeaux
- William Denorme
- Bryan Moreau

## Requirements
To run the project, you need:
- Git (if you are cloning the project)
- Docker

## Launch the project

First of all, you need to launch the docker deamon.

### Cloning project
You can launch this project by cloning it onto your computer and following a few steps :
#### With `bash`
- Clone this project: `git clone https://github.com/smart-home-controller/smart-home.git`.
- Run the [launch.sh](launch.sh) file located at the project's root with `./scripts/launch.sh`.

#### WIth `batch`
- Clone this project: `git clone https://github.com/smart-home-controller/smart-home.git`.
- Run the [launch.bat](launch.bat) file located at the project's root with : `.\scripts\launch.bat`.

#### Default installation
If you don't use the previously mentionned scripting languages, or want to set up everything manually, you can type the following commands :
- Clone this project: `git clone https://github.com/smart-home-controller/smart-home.git`.
- Initialize all submodules: `git submodule init`.
- Update all submodules so that you have the correct versions: `git submodule update`.
- Build and start all docker images and containers required for this project to successfully run: `docker compose --profile all up -d` (You can choose another profile besides `all`. Several are available, each launching a part of the application: `broker` `database` `api` `backend` `frontend`).
### Pulling docker images
`TODO` @Geoffrey \
When the project is running, connect all your devices to the same network.

`TODO` : add the links to the submodules docs @William