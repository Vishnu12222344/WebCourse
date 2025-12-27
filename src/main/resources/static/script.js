const API_URL = "http://localhost:8080/courses";

// Select DOM elements
const form = document.getElementById('course-form');
const courseList = document.getElementById('course-list');
const submitBtn = document.getElementById('submit-btn');
const cancelBtn = document.getElementById('cancel-btn');
const formTitle = document.getElementById('form-title');

// Initialize: Load courses on startup
document.addEventListener('DOMContentLoaded', fetchCourses);

// --- 1. GET ALL COURSES ---
async function fetchCourses() {
    try {
        const response = await fetch(API_URL);
        const courses = await response.json();
        renderTable(courses);
    } catch (error) {
        console.error("Error fetching courses:", error);
    }
}

function renderTable(courses) {
    courseList.innerHTML = courses.map(course => `
        <tr>
            <td><strong>${course.courseName}</strong></td>
            <td>${course.listedOn}</td>
            <td>₹${course.price.toFixed(2)}</td>
            <td>
                <button class="btn-edit" onclick="loadCourseIntoForm(${course.courseId})">Edit</button>
                <button class="btn-danger" onclick="deleteCourse(${course.courseId})">Delete</button>
            </td>
        </tr>
    `).join('');
}

// --- 2. ADD or UPDATE COURSE ---
form.onsubmit = async (e) => {
    e.preventDefault();
    clearErrors();

    const id = document.getElementById('courseId').value;
    const courseData = {
        courseName: document.getElementById('courseName').value,
        courseDescription: document.getElementById('courseDescription').value,
        listedOn: document.getElementById('listedOn').value,
        price: document.getElementById('price').value
    };

    // If ID exists, it's a PUT (update), otherwise it's a POST (add)
    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_URL}/${id}` : `${API_URL}/addcourse`;

    try {
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(courseData)
        });

        if (response.ok) {
            exitEditMode();
            fetchCourses();
        } else {
            const errorData = await response.json();
            displayValidationErrors(errorData);
        }
    } catch (error) {
        alert("Server communication error.");
    }
};

// --- 3. EDIT MODE LOGIC ---
async function loadCourseIntoForm(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const course = await response.json();

        // Populate fields
        document.getElementById('courseId').value = course.courseId;
        document.getElementById('courseName').value = course.courseName;
        document.getElementById('courseDescription').value = course.courseDescription;
        document.getElementById('listedOn').value = course.listedOn;
        document.getElementById('price').value = course.price;

        // Update UI state
        formTitle.innerText = "Editing Course ID: " + id;
        submitBtn.innerText = "Update Course";
        cancelBtn.style.display = "inline-block";
        window.scrollTo(0, 0); // Scroll to form
    } catch (error) {
        alert("Could not load course details.");
    }
}

function exitEditMode() {
    form.reset();
    document.getElementById('courseId').value = '';
    formTitle.innerText = "Add New Course";
    submitBtn.innerText = "Save Course";
    cancelBtn.style.display = "none";
    clearErrors();
}

// --- 4. DELETE COURSE ---
async function deleteCourse(id) {
    if (confirm("Are you sure you want to delete this course?")) {
        try {
            await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
            fetchCourses();
        } catch (error) {
            alert("Error deleting course.");
        }
    }
}

// --- 5. ERROR HANDLING ---
function displayValidationErrors(errors) {
    for (const field in errors) {
        const errorElement = document.getElementById(`err-${field}`);
        if (errorElement) {
            errorElement.innerText = errors[field];
        }
    }
}

function clearErrors() {
    document.querySelectorAll('.error-msg').forEach(el => el.innerText = '');
}


// Create starfield
function createStars() {
    const starsContainer = document.getElementById('stars');
    const starCount = 200;

    for (let i = 0; i < starCount; i++) {
        const star = document.createElement('div');
        star.className = 'star';
        star.style.left = Math.random() * 100 + '%';
        star.style.top = Math.random() * 100 + '%';
        star.style.animationDelay = Math.random() * 3 + 's';
        star.style.animationDuration = (Math.random() * 3 + 2) + 's';
        starsContainer.appendChild(star);
    }

    // Create shooting stars
    for (let i = 0; i < 5; i++) {
        const shootingStar = document.createElement('div');
        shootingStar.className = 'shooting-star';
        shootingStar.style.left = Math.random() * 100 + '%';
        shootingStar.style.top = Math.random() * 50 + '%';
        shootingStar.style.width = (Math.random() * 100 + 50) + 'px';
        shootingStar.style.animationDelay = Math.random() * 5 + 's';
        shootingStar.style.animationDuration = (Math.random() * 2 + 1) + 's';
        starsContainer.appendChild(shootingStar);
    }

    // Create cosmic dust
    for (let i = 0; i < 30; i++) {
        const dust = document.createElement('div');
        dust.className = 'cosmic-dust';
        dust.style.left = Math.random() * 100 + '%';
        dust.style.top = Math.random() * 100 + '%';
        dust.style.animationDelay = Math.random() * 20 + 's';
        dust.style.animationDuration = (Math.random() * 10 + 15) + 's';
        document.body.appendChild(dust);
    }
}

createStars();