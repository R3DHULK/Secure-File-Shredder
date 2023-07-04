import tkinter as tk
from tkinter import filedialog, messagebox
import os
import shutil

def shred_file(file_path):
    try:
        os.remove(file_path)
        messagebox.showinfo("Success", "File shredded successfully!")
    except OSError:
        messagebox.showerror("Error", "An error occurred while shredding the file.")

def shred_folder(folder_path):
    try:
        shutil.rmtree(folder_path)
        messagebox.showinfo("Success", "Folder shredded successfully!")
    except OSError:
        messagebox.showerror("Error", "An error occurred while shredding the folder.")

def select_file():
    file_path = filedialog.askopenfilename()
    if file_path:
        shred_file(file_path)

def select_folder():
    folder_path = filedialog.askdirectory()
    if folder_path:
        shred_folder(folder_path)

root = tk.Tk()
root.title("Secure File Shredder")

file_button = tk.Button(root, text="Select File", command=select_file)
file_button.pack(pady=10)

folder_button = tk.Button(root, text="Select Folder", command=select_folder)
folder_button.pack(pady=10)

root.mainloop()
