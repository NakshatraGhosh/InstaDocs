document.getElementById('sendButton').addEventListener('click', function() {
    const userMessage = document.getElementById('userMessage').value;
    const chatBox = document.getElementById('chat-box');

    if (userMessage.trim() !== "") {
        // Add user message to the chat box
        chatBox.innerHTML += `<p><strong>You:</strong> ${userMessage}</p>`;
        document.getElementById('userMessage').value = '';

        // Send message to the backend (Spring Boot)
        fetch('/api/chat', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userMessage)
        })
        .then(response => response.text())
        .then(data => {
            // Add Gemini AI's response to the chat box
            chatBox.innerHTML += `<p><strong>Bot:</strong> ${data}</p>`;
            chatBox.scrollTop = chatBox.scrollHeight;
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
});
/**
 * 
 */