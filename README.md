# Eclipse Import Projects Plug-in

This plug-in allows import of projects using an Eclipse command-line parameter. It can be used to automate the use of a plug-in over the life of a project.

## Installation

Place the [com.seeq.eclipse.importprojects](jar/com.seeq.eclipse.importprojects_1.1.0.jar) file in the `<eclipse location>/dropins/` folder.

**Please note** this will vary depending on the eclipse version that is used. Please see the the [Development and Deployment](#development-and-deployment) section for more details about creating the plug-in jar.

## Usage

When launching Eclipse two commands are available:

- `-import <root project folder>`
- `-exit_on_finish`

The first will cause Eclipse to recursively search the supplied folder for *.project* files and import them into the workspace. If they are already present in the workspace, they will be deleted and re-created (hard refresh).

The second will cause eclipse to exit once it has finished adding and refreshing the projects. Usage only make sense to be used in-conjunction with the first. 

## Example Usage

### Linux

1. Import and open Eclipse
./eclipse -console -noSplash -data ~/workspace -import ~/eclipse-import-projects-plugin

2. Import and exit Eclipse once the project has been imported.
./eclipse -console -noSplash -data ~/workspace -import ~/eclipse-import-projects-plugin -exit_on_finish

## Development and Deployment

1 Import the project into Eclipse Luna

2. Open Eclipse and go to File > Import...

3. Under the General section select Existing Projects into Workspace and click next

4. Click Browse and navigate to the location of the eclipse project.

5. Ensure the check box is clicked for the project and click Finish.

6. Right Click on the project and click Export...

7. Under Plug-in Development Click on 'Deployable plug-in and fragments' and click next

8. Ensure the project is checked and click Finish

9. Copy plug-in Jar from `<workspace folder>/plugins` into `<eclipse location>/dropins/`

10. Restart Eclipse.

## License

MIT License

Copyright (C) 2014 Seeq Corporation


Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
