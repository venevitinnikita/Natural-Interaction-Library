import QtQuick 2.6
import QtQuick.Controls 1.5
import QtQuick.Dialogs 1.2

ApplicationWindow {
    visible: true
    width: 640
    height: 480
    title: qsTr("Hello World")

    menuBar: MenuBar {
        Menu {
            title: qsTr("File")
            MenuItem {
                text: qsTr("Exit")
                onTriggered: Qt.quit();
            }
        }
    }

    Rectangle {
        width: 300
        height: 200
        anchors.centerIn: parent

        MainForm {
            anchors.fill: parent
            button1.onClicked: status.text = qsTr("Button 1 pressed")
            button2.onClicked: status.text = qsTr("Button 2 pressed")

            Text {
                text: qsTr("Buttons")
                anchors.horizontalCenter: parent.horizontalCenter
                anchors.top: parent.top
            }

            Text {
                id: status
                anchors.horizontalCenter: parent.horizontalCenter
                anchors.bottom: parent.bottom
            }
        }
    }
}
