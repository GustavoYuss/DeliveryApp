let figureCount = 0;
let mouseX = 0, mouseY = 0;
let currentTextElement = null;
let clickedOnce = false;

document.addEventListener('DOMContentLoaded', () => {
    const canvas = document.getElementById("Canvas");
    canvas.addEventListener('click', showFigureSelector);
})

function drawRectangle() {
    removeEventListener();
    const canvas = document.getElementById("Canvas");

    canvas.addEventListener('click', drawFigure);
    function drawFigure(e) {

        const newRectangle = document.createElementNS("http://www.w3.org/2000/svg", "polygon");

        newRectangle.setAttribute("id", `rectangle${figureCount}`);
        newRectangle.setAttribute("class", "move");
        newRectangle.setAttribute("points", "-100,50 100,50 100,-50 -100,-50");
        newRectangle.setAttribute("fill", "blue");
        newRectangle.setAttribute("stroke", "black");
        newRectangle.setAttribute("stroke-width", "2");

        const point = canvas.createSVGPoint();
        point.x = e.clientX;
        point.y = e.clientY;

        const svgPoint = point.matrixTransform(canvas.getScreenCTM().inverse());

        const x = svgPoint.x;
        const y = svgPoint.y;
        newRectangle.setAttribute("transform", `translate(${x}, ${y}) rotate(0)`);

        canvas.appendChild(newRectangle);

        canvas.removeEventListener('click', drawFigure);
        newRectangle.addEventListener('mousedown',showFigureSelector);
        newRectangle.addEventListener('mousedown', moveFigure);

        figureCount++;
    }
}

function drawStart() {

    removeEventListener();
    const canvas = document.getElementById("Canvas");
    canvas.addEventListener('click', drawFigure);

    function drawFigure(e) {

        const newStar = document.createElementNS("http://www.w3.org/2000/svg", "polygon");

        newStar.setAttribute("id", `star${figureCount}`);
        newStar.setAttribute("class", "move");
        newStar.setAttribute("points", "0,-50 14,-16 47,-16 19,6 30,40 0,20 -30,40 -19,6 -47,-16 -14,-16");
        newStar.setAttribute("fill", "gold");
        newStar.setAttribute("stroke", "black");
        newStar.setAttribute("stroke-width", "2");

        const point = canvas.createSVGPoint();
        point.x = e.clientX;
        point.y = e.clientY;

        const svgPoint = point.matrixTransform(canvas.getScreenCTM().inverse());

        const x = svgPoint.x;
        const y = svgPoint.y;
        newStar.setAttribute("transform", `translate(${x}, ${y}) rotate(0)`);

        canvas.appendChild(newStar);

        canvas.removeEventListener('click', drawFigure);
        newStar.addEventListener('mousedown',showFigureSelector);
        newStar.addEventListener('mousedown', moveFigure);

        figureCount++;
    }
}

function drawTriangle() {

    removeEventListener();
    const canvas = document.getElementById("Canvas");

    canvas.addEventListener("click", drawFigure);

    function drawFigure(e) {

        const triangle = document.createElementNS("http://www.w3.org/2000/svg", "polygon");

        triangle.setAttribute("points", "0,-50 50,50 -50,50");
        triangle.setAttribute("fill", "lightblue");
        triangle.setAttribute("stroke", "black");
        triangle.setAttribute("stroke-width", "2");
        triangle.setAttribute("class", "move");
        triangle.setAttribute("id", `triangle${figureCount}`);

        const point = canvas.createSVGPoint();
        point.x = e.clientX;
        point.y = e.clientY;

        const svgPoint = point.matrixTransform(canvas.getScreenCTM().inverse());
        const x = svgPoint.x;
        const y = svgPoint.y;

        triangle.setAttribute("transform", `translate(${x}, ${y}) rotate(0)`);

        canvas.appendChild(triangle);
        triangle.addEventListener('mousedown', moveFigure);
        canvas.addEventListener('mousedown',showFigureSelector);
        canvas.removeEventListener("click", drawFigure);

        figureCount++;
    }
}

function drawLine() {
    removeEventListener();
    const canvas = document.getElementById("Canvas");

    canvas.addEventListener('click', drawFigure);
    function drawFigure(e) {

        const newLine = document.createElementNS("http://www.w3.org/2000/svg", "line");

        newLine.setAttribute("id", `line${figureCount}`);
        newLine.setAttribute("class", "move");
        newLine.setAttribute("x1", "-50");
        newLine.setAttribute("y1", "0");
        newLine.setAttribute("x2", "50");
        newLine.setAttribute("y2", "0");
        newLine.setAttribute("stroke", "black");
        newLine.setAttribute("stroke-width", "5");

        const point = canvas.createSVGPoint();
        point.x = e.clientX;
        point.y = e.clientY;

        const svgPoint = point.matrixTransform(canvas.getScreenCTM().inverse());

        const x = svgPoint.x;
        const y = svgPoint.y;
        newLine.setAttribute("transform", `translate(${x}, ${y}) rotate(0)`);

        canvas.appendChild(newLine);

        canvas.removeEventListener('click', drawFigure);
        newLine.addEventListener('mousedown',showFigureSelector);
        newLine.addEventListener('mousedown', moveFigure);

        figureCount++;
    }
}

function writeText() {
    removeEventListener();

    const canvas = document.getElementById("Canvas");
    canvas.addEventListener('click', writeText);

    function writeText(e) {
        const newText = document.createElementNS("http://www.w3.org/2000/svg", "text");

        newText.setAttribute("id", `text${figureCount}`);
        newText.setAttribute("class", "move");
        newText.setAttribute("x", "0");
        newText.setAttribute("y", "0");
        newText.setAttribute("fill", "black");
        newText.setAttribute("font-family", "Arial");
        newText.setAttribute("font-size", "20");
        newText.setAttribute("text-anchor", "middle");
        newText.setAttribute("dominant-baseline", "middle");
        newText.setAttribute("style", "user-select: none");


        newText.textContent = "|";

        const point = canvas.createSVGPoint();
        point.x = e.clientX;
        point.y = e.clientY;

        const svgPoint = point.matrixTransform(canvas.getScreenCTM().inverse());

        const x = svgPoint.x;
        const y = svgPoint.y;

        newText.setAttribute("transform", `translate(${x}, ${y}) rotate(0)`);

        canvas.appendChild(newText);

        canvas.removeEventListener('click', writeText);
        newText.addEventListener('mousedown',showFigureSelector);
        newText.addEventListener('mousedown', moveFigure);

        currentTextElement = newText;

        document.addEventListener("keydown", keydownHandler);
        document.addEventListener("click", removeTextInput)

        figureCount++;
    }
}

function removeTextInput() {
    if (!clickedOnce) {
        clickedOnce = true;
        return;
    }

    document.removeEventListener("keydown", keydownHandler);
    document.removeEventListener("click", removeTextInput);

    const currentText = currentTextElement.textContent;
    if (currentText.length > 1) {
        currentTextElement.textContent = currentText.slice(0, -1);
        document.removeEventListener("keydown", keydownHandler);
    } else {
        currentTextElement.remove();
        document.removeEventListener("keydown", keydownHandler);
    }

    clickedOnce = false;
}

function keydownHandler(event) {
    inputTextUser(event, currentTextElement);
}

function inputTextUser(event, textElement) {
    if (event.key.length === 1) {
        const currentText = textElement.textContent;
        textElement.textContent = currentText.slice(0, -1) + event.key + currentText.slice(-1);
    } else if (event.key === "Backspace") {
        const currentText = textElement.textContent;
        if (currentText.length > 1) {
            textElement.textContent = currentText.slice(0, -2) + currentText.slice(-1);
        }
    } else if (event.key === "Enter") {
        const currentText = textElement.textContent;
        if (currentText.length > 1) {
            textElement.textContent = currentText.slice(0, -1);
            document.removeEventListener("keydown", keydownHandler);
            document.removeEventListener("click", removeTextInput);
        } else {
            textElement.remove();
            document.removeEventListener("keydown", keydownHandler);
            document.removeEventListener("click", removeTextInput);
        }
    }
}

function getFigurePoints(figure) {

    let minX, maxX, minY, maxY;

    if (figure.tagName.toLowerCase() === "text") {

        const bbox = figure.getBBox();
        minX = bbox.x;
        maxX = bbox.x + bbox.width;
        minY = bbox.y;
        maxY = bbox.y + bbox.height;

    } else if (figure.tagName.toLowerCase() === "line") {

        const x1 = parseFloat(figure.getAttribute("x1"));
        const y1 = parseFloat(figure.getAttribute("y1"));
        const x2 = parseFloat(figure.getAttribute("x2"));
        const y2 = parseFloat(figure.getAttribute("y2"));

        minX = (Math.min(x1, x2)) + 5;
        maxX = (Math.max(x1, x2)) + 5;
        minY = (Math.min(y1, y2)) + 5;
        maxY = (Math.max(y1, y2)) + 5;

    } else {

        const points = figure.points;
        minX = Infinity;
        maxX = -Infinity;
        minY = Infinity;
        maxY = -Infinity;

        for (let i = 0; i < points.length; i++) {
            const point = points[i];
            if (point.x < minX) minX = point.x;
            if (point.x > maxX) maxX = point.x;
            if (point.y < minY) minY = point.y;
            if (point.y > maxY) maxY = point.y;
        }

    }

    return [minX, maxX, minY, maxY];
}

function showFigureSelector(figure) {
    const targetElement = figure.target;
    const selector = document.getElementById("Selector");

    if(targetElement.classList.contains("move")) {
        const points = getFigurePoints(figure.target);
        if (selector != null) {
            selector.style.display = "block";
            editFigureSelector(figure, points);
        } else {
            drawFigureSelector(figure);
        }
    } else {
        if (selector) {
            selector.style.display = "none";
        }
    }
}

function drawFigureSelector(figure) {

    const canvas = document.getElementById("Canvas");

    let [minX, maxX, minY, maxY] = getFigurePoints(figure.target);
    let width = ((maxX - minX) / 2) + 10;
    let height = ((maxY - minY) / 2) + 10;

    const newRectangle = document.createElementNS("http://www.w3.org/2000/svg", "polygon");

    newRectangle.setAttribute("id", `Selector`);
    newRectangle.setAttribute("class", "move");
    newRectangle.setAttribute("points", `${(width * -1)},${height} ${width},${height} ${width},${(height * -1)} ${(width * -1)},${(height * -1)}`);
    newRectangle.setAttribute("fill", "none");
    newRectangle.setAttribute("stroke", "white");
    newRectangle.setAttribute("stroke-width", "2");
    newRectangle.setAttribute("stroke-dasharray", "5,5");

    newRectangle.setAttribute("transform", `translate(0, 0), rotate(0)`);


    const regex = /translate\((\d+),\s*(\d+)\)/;

    let transformValue = figure.target.getAttribute("transform");
    const match = transformValue.match(regex);

    if (match) {
        const x = parseFloat(match[1]);
        const y = parseFloat(match[2]);
        const angle = getRotateAngle(figure.target);
        newRectangle.setAttribute("transform", `translate(${x}, ${y}), rotate(${angle})`);
    } else {
        console.log("No match found");
    }
    canvas.appendChild(newRectangle);
}

function editFigureSelector(figure, points) {

    let width = ((points[1] - points[0]) / 2) + 10;
    let height = ((points[3] - points[2]) / 2) + 10;

    const selector = document.getElementById("Selector");
    let transformValue = figure.target.getAttribute("transform");

    selector.setAttribute("points", `${(width * -1)},${height} ${width},${height} ${width},${(height * -1)} ${(width * -1)},${(height * -1)}`);

    const regex = /translate\((\d+),\s*(\d+)\)/;

    const match = transformValue.match(regex);
    if (match) {
        const x = parseFloat(match[1]);
        const y = parseFloat(match[2]);
        const angle = getRotateAngle(figure.target);
        editTranslateFigure(selector, x, y);
        selector.setAttribute("transform", `translate(${x}, ${y}), rotate(${angle})`);
        figure.target.removeEventListener('click', drawFigureSelector);
    } else {
        console.log("No match Found");
    }
}

function moveFigure(event) {

    const canvas = document.getElementById("Canvas");
    const selector = document.getElementById("Selector");
    const target = event.target;

    let figureWidth, figureHeight, minX, maxX, minY, maxY;

    [minX, maxX, minY, maxY] = getFigurePoints(event.target);

    figureWidth = maxX - minX;
    figureHeight = maxY - minY;

    event.target.addEventListener('mousemove', moveFigureCanvas);

    function moveFigureCanvas(e) {
        const point = canvas.createSVGPoint();
        point.x = e.clientX;
        point.y = e.clientY;

        const svgPoint = point.matrixTransform(canvas.getScreenCTM().inverse());
        mouseX = svgPoint.x;
        mouseY = svgPoint.y;

        const canvasRect = canvas.getBoundingClientRect();
        const canvasWidth  = canvasRect.width;
        const canvasHeight  = canvasRect.height;

        const translateX = mouseX - (minX + figureWidth / 2);
        const translateY = mouseY - (minY + figureHeight / 2);

        const newMinX = minX + translateX;
        const newMaxX = maxX + translateX;
        const newMinY = minY + translateY;
        const newMaxY = maxY + translateY;

        if (newMinX < 0) {
            mouseX += Math.abs(newMinX);
        }
        if (newMaxX > canvasWidth) {
            mouseX -= newMaxX - canvasWidth;
        }
        if (newMinY < 0) {
            mouseY += Math.abs(newMinY);
        }
        if (newMaxY > canvasHeight) {
            mouseY -= newMaxY - canvasHeight;
        }

        editTranslateFigure(target, mouseX, mouseY);
        editTranslateFigure(selector, mouseX, mouseY);
    }

    event.target.addEventListener('mouseup', mouseup);
    function mouseup() {
        event.target.removeEventListener('mousemove', moveFigureCanvas);
    }
}

function activeRotate() {
    removeEventListener();
    const elements = document.querySelectorAll('.move');
    elements.forEach((element) => {
        element.addEventListener('dblclick', rotateFigure);
    });
}

function rotateFigure(event) {
    let figure = event.target;
    const selector = document.getElementById("Selector");
    editRotateFigure(figure);
    editRotateFigure(selector);
}

function activeDeleteFigure() {
    document.addEventListener('click', deleteFigure);
}

function deleteFigure(event) {
    const target = event.target;
    if (target.classList.contains("move")) {
        const selector = document.getElementById("Selector");
        target.remove();
        selector.style.display = "none";
    }
}

function removeEventListener() {
    document.removeEventListener('click', deleteFigure);
}

function editTranslateFigure(figure, x, y) {
    const regex = /translate\((\d+),\s*(\d+)\)/;
    let transformValue = figure.getAttribute("transform");
    const match = transformValue.match(regex);
    if (match) {
        const updatedTransform = transformValue.replace(regex, `translate(${x}, ${y})`);
        figure.setAttribute("transform", updatedTransform);
    } else {
        console.log("No match found");
    }
}

function editRotateFigure(figure) {
    const regex = /rotate\((\d+)\)/;
    let transformValue = figure.getAttribute("transform");
    const match = transformValue.match(regex);

    if (match) {
        let angle = parseInt(match[1]);

        if (angle === 360) {
            angle = 90;
        } else {
            angle += 90;
        }

        const updatedTransform = transformValue.replace(regex, `rotate(${angle})`);
        figure.setAttribute("transform", updatedTransform);
    } else {
        const updatedTransform = transformValue
            ? `${transformValue} rotate(${0})`
            : `rotate(${0})`;
        figure.setAttribute("transform", updatedTransform);
    }
}

function getRotateAngle(figure) {
    const regex = /rotate\((\d+)\)/;
    let transformValue = figure.getAttribute("transform");
    const match = transformValue.match(regex);
    if (match) {
        return parseInt(match[1]);
    }
}

function saveCanvas() {
    const canvas = document.getElementById("Canvas");
    const serializer = new XMLSerializer();
    const svgContent = serializer.serializeToString(canvas);

    const fullContent = `
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Saved Canvas</title>
            <script src="/Js/index.js"></script>
            <link rel="stylesheet" href="/Styles/index.css">
        </head>
        <body>
            <div id="CanvasContainer">
                <svg id="Canvas" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1500 500">
                    ${svgContent}
                </svg>
            </div>
        </body>
        </html>
    `;

    const blob = new Blob([fullContent], { type: 'text/html' });
    const a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = 'canvas.html';
    a.click();
}

function loadCanvas(event) {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function(e) {
        const content = e.target.result;
        const parser = new DOMParser();
        const doc = parser.parseFromString(content, 'text/html');
        const loadedSVG = doc.querySelector('svg#Canvas');

        if (loadedSVG) {
            const canvasContainer = document.getElementById("CanvasContainer");
            canvasContainer.innerHTML = ''; // Limpia el contenedor
            canvasContainer.appendChild(loadedSVG); // Carga el nuevo SVG
        } else {
            alert("No se encontró un lienzo válido en el archivo.");
        }
    };
    reader.readAsText(file);
}


/*--------------------------------------*/

function enableFigureMovement() {
    const figures = document.querySelectorAll(".move"); // Todas las figuras con clase "move"

    figures.forEach(figure => {
        figure.addEventListener("mousedown", startMovingFigure);
    });
}

function startMovingFigure(event) {
    const canvas = document.getElementById("Canvas");
    const figure = event.target;

    let offsetX, offsetY;

    const transform = figure.getAttribute("transform") || "translate(0, 0)";
    const match = /translate\(([^,]+),\s*([^)]+)\)/.exec(transform);
    const initialX = match ? parseFloat(match[1]) : 0;
    const initialY = match ? parseFloat(match[2]) : 0;

    const startX = event.clientX;
    const startY = event.clientY;

    offsetX = initialX;
    offsetY = initialY;

    function moveFigure(event) {
        const deltaX = event.clientX - startX;
        const deltaY = event.clientY - startY;

        const newX = offsetX + deltaX;
        const newY = offsetY + deltaY;

        figure.setAttribute("transform", `translate(${newX}, ${newY})`);
    }

    function stopMovingFigure() {
        canvas.removeEventListener("mousemove", moveFigure);
        canvas.removeEventListener("mouseup", stopMovingFigure);
    }

    canvas.addEventListener("mousemove", moveFigure);
    canvas.addEventListener("mouseup", stopMovingFigure);
}

document.addEventListener("DOMContentLoaded", () => {
    enableFigureMovement();
});

